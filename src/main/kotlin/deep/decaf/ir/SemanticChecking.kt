package deep.decaf.ir

data class SemanticErrorMessage(val message: String, val position: Position) {
    override fun toString(): String =
        if (position != Position.unknown()) "${position.line}:${position.column} $message"
        else message
}

fun checkSemantics(program: IRProgram): List<SemanticErrorMessage> {
    val errorList = mutableListOf<SemanticErrorMessage>()
    var mainPresent = false
    val env = Env()

    fun check(node: IR): Type {
        return when (node) {
            is IRProgram -> {
                if (node.name != "Program") {
                    errorList.add(SemanticErrorMessage("class name must be Program", Position.unknown()))
                }
                var noErrors = true
                node.fieldDeclarations.forEach {
                    val type = check(it)
                    if (type == Type.ERROR) noErrors = false
                }
                node.methodDeclarations.forEach {
                    val type = check(it)
                    if (type == Type.ERROR) noErrors = false
                }
                if (noErrors) Type.VOID else Type.ERROR
            }
            is IRIntLiteral -> node.type
            is IRBoolLiteral -> node.type
            is IRMethodCallExpr -> {
                val signature = env.methodSignatureBindings[node.name]
                if (signature != null) {
                    var hasErrors = false
                    if (signature.argList.size != node.argList.size) {
                        hasErrors = true
                        errorList.add(
                            SemanticErrorMessage(
                                "wrong no. of arguments - expected ${signature.argList.size}, got ${node.argList.size}",
                                node.pos
                            )
                        )
                    } else {
                        for ((idx, arg) in signature.argList.withIndex()) {
                            val exprType = check(node.argList[idx])
                            if (exprType != arg.type) {
                                hasErrors = true
                                errorList.add(
                                    SemanticErrorMessage(
                                        "type of ${idx + 1}th actual param doesn't match type of formal param \"${arg.name}\" of method \"${node.name}\"",
                                        node.pos
                                    )
                                )
                            }
                        }
                    }
                    if (hasErrors) Type.ERROR else signature.returnType
                } else {
                    errorList.add(SemanticErrorMessage("method ${node.name} not defined yet", node.pos))
                    Type.ERROR
                }
            }
            is IRCallOutExpr -> Type.INT
            is IRBinOpExpr -> {
                val leftType = check(node.left)
                val rightType = check(node.right)
                when {
                    leftType == Type.ERROR || rightType == Type.ERROR -> {
                        Type.ERROR
                    }
                    leftType == Type.VOID || rightType == Type.VOID -> {
                        errorList.add(SemanticErrorMessage("expression to ${node.op} cannot be void", node.pos))
                        Type.ERROR
                    }
                    else -> {
                        when (node.op) {
                            BinOp.ADD, BinOp.SUBTRACT, BinOp.MULTIPLY, BinOp.DIVIDE, BinOp.REMAINDER ->
                                if (leftType == Type.INT && rightType == Type.INT) Type.INT
                                else {
                                    errorList.add(
                                        SemanticErrorMessage(
                                            "operands to ${node.op} must be integer",
                                            node.pos
                                        )
                                    )
                                    Type.ERROR
                                }
                            BinOp.LESS, BinOp.MORE, BinOp.LESS_OR_EQ, BinOp.MORE_OR_EQ ->
                                if (leftType == Type.INT && rightType == Type.INT) Type.BOOL
                                else {
                                    errorList.add(
                                        SemanticErrorMessage(
                                            "operands to ${node.op} must be integer",
                                            node.pos
                                        )
                                    )
                                    Type.ERROR
                                }
                            BinOp.EQ, BinOp.NOT_EQ ->
                                if (leftType == rightType) Type.BOOL
                                else {
                                    errorList.add(
                                        SemanticErrorMessage(
                                            "operands to ${node.op} must be of same type",
                                            node.pos
                                        )
                                    )
                                    Type.ERROR
                                }
                            BinOp.OR, BinOp.AND ->
                                if (leftType == Type.BOOL && rightType == Type.BOOL) Type.BOOL
                                else {
                                    errorList.add(
                                        SemanticErrorMessage(
                                            "operands to ${node.op} must be boolean",
                                            node.pos
                                        )
                                    )
                                    Type.ERROR
                                }
                        }
                    }
                }
            }
            is IRUnaryOpExpr -> {
                when (val type = check(node.expr)) {
                    Type.ERROR -> {
                        Type.ERROR
                    }
                    Type.VOID -> {
                        errorList.add(
                            SemanticErrorMessage(
                                "cannot apply ${node.op} to expression of type void",
                                node.pos
                            )
                        )
                        Type.ERROR
                    }
                    else -> {
                        when (node.op) {
                            UnaryOp.MINUS -> {
                                if (type == Type.INT) Type.INT
                                else {
                                    errorList.add(
                                        SemanticErrorMessage(
                                            "cannot apply ${node.op} to expression of type boolean",
                                            node.pos
                                        )
                                    )
                                    Type.ERROR
                                }
                            }
                            UnaryOp.NOT -> {
                                if (type == Type.BOOL) Type.BOOL
                                else {
                                    errorList.add(
                                        SemanticErrorMessage(
                                            "cannot apply ${node.op} to expression of type int",
                                            node.pos
                                        )
                                    )
                                    Type.ERROR
                                }
                            }
                        }
                    }
                }
            }
            is IRLocationExpression -> check(node.location)
            is IRIDLocation -> {
                val type = env.getNonArrayVarBinding(node.name)
                if (type != null) {
                    type
                } else {
                    errorList.add(SemanticErrorMessage("${node.name} has not been declared", node.pos))
                    Type.ERROR
                }
            }
            is IRArrayLocation -> {
                val signature = env.arrayFieldBindings[node.name]
                val indexType = check(node.indexExpr)
                if (signature != null) {
                    if (indexType == Type.INT) signature.type
                    else {
                        errorList.add(SemanticErrorMessage("array index must be an integer", node.pos))
                        Type.ERROR
                    }
                } else {
                    errorList.add(SemanticErrorMessage("${node.name} has not been declared", node.pos))
                    Type.ERROR
                }
            }
            is IRBlock -> {
                env.enterBlock()
                var hasErrors = false
                node.fieldDeclarations.forEach {
                    val type = check(it)
                    if (type == Type.ERROR) hasErrors = true
                }
                var type = Type.VOID
                node.statements.forEach {
                    type = check(it)
                    if (type == Type.ERROR) hasErrors = true
                }
                env.leaveBlock()
                if (hasErrors) Type.ERROR else type
            }
            is IRVarDeclaration -> {
                val present = env.addVariableBinding(node.name, node.type)
                if (present) {
                    errorList.add(
                        SemanticErrorMessage(
                            "${node.name} declared more than once in the same scope", node.pos
                        )
                    )
                    Type.ERROR
                } else {
                    Type.VOID
                }
            }
            is IRRegularFieldDeclaration -> {
                val present = env.addRegularFieldBinding(node.name, node.type)
                if (present) {
                    errorList.add(
                        SemanticErrorMessage(
                            "${node.name} declared more than once in the same scope", node.pos
                        )
                    )
                    Type.ERROR
                } else {
                    Type.VOID
                }
            }
            is IRArrayFieldDeclaration -> {
                val present =
                    env.arrayFieldBindings.addNoOverwrite(node.name, ArrayFieldSignature(node.type, node.size))
                when {
                    present -> {
                        errorList.add(
                            SemanticErrorMessage(
                                "${node.name} declared more than once in the same scope", node.pos
                            )
                        )
                        Type.ERROR
                    }
                    node.size <= 0 -> {
                        errorList.add(SemanticErrorMessage("array size must be positive", node.pos))
                        Type.ERROR
                    }
                    else -> {
                        Type.VOID
                    }
                }
            }
            is IRMethodDeclaration -> {
                val present =
                    env.methodSignatureBindings.addNoOverwrite(node.name, MethodSignature(node.type, node.argList))
                if (present) {
                    errorList.add(
                        SemanticErrorMessage(
                            "${node.name} declared more than once in the same scope", node.pos
                        )
                    )
                    Type.ERROR
                } else {
                    if (node.name == "main") {
                        mainPresent = true
                    }
                    val tmp = env.enclosingMethodName
                    env.enclosingMethodName = node.name
                    env.enterBlock()
                    node.argList.forEach { env.addVariableBinding(it.name, it.type) }
                    val retType = check(node.block)
                    env.leaveBlock()
                    env.enclosingMethodName = tmp
                    if (retType == Type.VOID && node.type != Type.VOID) {
                        errorList.add(SemanticErrorMessage("expected ${node.name} to return ${node.type}", node.pos))
                        Type.ERROR
                    } else retType
                }
            }
            is IRDirectAssignStatement -> {
                val locationType = check(node.location)
                val exprType = check(node.expr)
                if (exprType != Type.ERROR && locationType != Type.ERROR && exprType != locationType) {
                    errorList.add(
                        SemanticErrorMessage(
                            "value of type $exprType cannot be assigned to $locationType",
                            node.pos
                        )
                    )
                    Type.ERROR
                } else Type.VOID
            }
            is IRIncrementStatement -> {
                val locationType = check(node.location)
                val exprType = check(node.expr)
                if (exprType != Type.INT && locationType != Type.INT) {
                    errorList.add(SemanticErrorMessage("cannot increment $locationType by $exprType", node.pos))
                    Type.ERROR
                } else Type.VOID
            }
            is IRDecrementStatement -> {
                val locationType = check(node.location)
                val exprType = check(node.expr)
                if (exprType != Type.INT && locationType != Type.INT) {
                    errorList.add(SemanticErrorMessage("cannot decrement $locationType by $exprType", node.pos))
                    Type.ERROR
                } else Type.VOID
            }
            is IRBreakStatement -> {
                if (!env.inLoop) {
                    errorList.add(SemanticErrorMessage("cannot break outside a loop", node.pos))
                    Type.ERROR
                } else Type.VOID
            }
            is IRContinueStatement -> {
                if (!env.inLoop) {
                    errorList.add(SemanticErrorMessage("cannot continue outside a loop", node.pos))
                    Type.ERROR
                } else Type.VOID
            }
            is IRIfStatement -> {
                val conditionType = check(node.condition)
                var hasErrors = false
                if (conditionType != Type.BOOL) {
                    hasErrors = true
                    errorList.add(SemanticErrorMessage("condition must be of boolean type", node.pos))
                }
                val ifBlockType = check(node.ifBlock)
                val elseBlockType = if (node.elseBlock != null) check(node.elseBlock) else Type.VOID
                if (ifBlockType != Type.ERROR && elseBlockType != Type.ERROR && hasErrors) Type.VOID else Type.ERROR
            }
            is IRForStatement -> {
                var hasErrors = false
                if (check(node.initExpr) != Type.INT) {
                    hasErrors = true
                    errorList.add(SemanticErrorMessage("init expr of loop must be of type int", node.pos))
                }
                if (check(node.condition) != Type.INT) {
                    hasErrors = true
                    errorList.add(SemanticErrorMessage("end expr of loop must be of type int", node.pos))
                }
                env.inLoop = true
                env.enterBlock()
                env.addVariableBinding(node.loopVar, Type.INT)
                if (check(node.body) == Type.ERROR) hasErrors = true
                env.leaveBlock()
                if (hasErrors) Type.ERROR else Type.VOID
            }
            is IRReturnStatement -> {
                val signature = env.methodSignatureBindings[env.enclosingMethodName]!!
                val type = if (node.expr != null) check(node.expr) else Type.VOID
                if (type != signature.returnType) {
                    errorList.add(
                        SemanticErrorMessage(
                            "${env.enclosingMethodName} requires to return ${signature.returnType}",
                            node.pos
                        )
                    )
                    Type.ERROR
                } else type
            }
            is IRInvokeStatement -> check(node.expr)
            is IRBlockStatement -> check(node.block)
            IRNone -> Type.ERROR
        }
    }

    check(program)
    if (!mainPresent)
        errorList.add(SemanticErrorMessage("main method not found", Position.unknown()))

    return errorList
}