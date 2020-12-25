package deep.decaf.low.amd64

import deep.decaf.ir.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class AsmProgramInfo {
    var loopCount = 0
    var ifCount = 0

    var stackSize = 0
        private set

    private val globalVariables = mutableMapOf<String, Type>() // name -> type
    private val globalArrays = mutableMapOf<String, Int>() // name -> size
    private val variableStacks = mutableListOf<MutableMap<String, MemLoc>>()
    private val methodFormalParamsStack = mutableListOf<Map<String, Location>>()
    val globalText = mutableMapOf<String, String>()

    fun addGlobalVariable(name: String, type: Type) {
        globalVariables[name] = type
    }

    fun addGlobalArray(name: String, size: Int) {
        globalArrays[name] = size
    }

    fun enterScope() {
        variableStacks.add(mutableMapOf())
    }

    fun leaveScope(): Int {
        val size = variableStacks.last().size
        variableStacks.removeAt(variableStacks.size - 1)
        stackSize -= size
        return size
    }

    fun addVariable(name: String): MemLoc {
        stackSize++
        val loc = MemLoc(
            Register.basePointer(),
            NumberOffset(-8 * stackSize)
        )
        variableStacks.last()[name] = loc
        return loc
    }

    fun getVariableLocation(name: String): MemLoc {
        for (locMap in variableStacks.reversed()) {
            if (name in locMap) {
                return locMap.getValue(name)
            }
        }
        if (name in methodFormalParamsStack.last()) {
            return methodFormalParamsStack.last()[name]!! as MemLoc
        }
        if (name in globalVariables) {
            return MemLoc(
                Register.instructionPointer(),
                StringOffset(name)
            )
        }
        throw IllegalStateException("variable $name not found")
    }

    fun pushStack() {
        stackSize++
    }

    fun popStack() {
        stackSize--
    }

    fun pushMethodArgs(args: Map<String, Location>) {
        methodFormalParamsStack.add(args)
    }

    fun popMethodArgs() {
        methodFormalParamsStack.removeAt(methodFormalParamsStack.size - 1)
    }

    fun addGlobalString(value: String): String {
        val label = "LOC${globalText.size}"
        globalText[label] = value
        return label
    }
}

fun irExprToLow(expr: IRExpr, info: AsmProgramInfo): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    fun traverse(expr: IRExpr): MemLoc {
        return when (expr) {
            is IRIntLiteral -> {
                instructions.add(MoveInstruction(ImmediateVal(expr.lit), Register.r10()))
                val tmp = info.addVariable(getUUID())
                instructions.add(PushInstruction(ImmediateVal(0)))
                instructions.add(MoveInstruction(Register.r10(), tmp))
                tmp
            }
            is IRBoolLiteral -> {
                instructions.add(MoveInstruction(ImmediateVal(if (expr.lit) 1 else 0), Register.r10()))
                val tmp = info.addVariable(getUUID())
                instructions.add(PushInstruction(ImmediateVal(0)))
                instructions.add(MoveInstruction(Register.r10(), tmp))
                tmp
            }
            is IRMethodCallExpr -> {
                val argLocations = expr.argList.map { traverse(it) }
                argLocations.forEach {
                    instructions.add(PushInstruction(it))
                    info.pushStack()
                }
                instructions.add(CallInstruction(expr.name))
                for (i in 1..expr.argList.size) {
                    instructions.add(PopInstruction(Register.r10()))
                    info.popStack()
                }
                val tmp = info.addVariable(getUUID())
                instructions.add(PushInstruction(ImmediateVal(0)))
                instructions.add(MoveInstruction(Register.returnRegister(), tmp))
                tmp
            }
            is IRCallOutExpr -> {
                for ((i, arg) in expr.argList.withIndex()) {
                    val register = when (i) {
                        0 -> Register("rdi")
                        1 -> Register("rsi")
                        2 -> Register("rdx")
                        3 -> Register("rcx")
                        4 -> Register("r8")
                        5 -> Register("r9")
                        else -> throw IllegalArgumentException("too many arguments to callout expression")
                    }
                    when (arg) {
                        is StringCallOutArg -> {
                            val loc = info.addGlobalString(arg.arg)
                            instructions.add(
                                MoveInstruction(
                                    MemLoc(Register.basePointer(), StringOffset(loc)),
                                    register
                                )
                            )
                        }
                        is ExprCallOutArg -> {
                            val loc = traverse(arg.arg)
                            instructions.add(MoveInstruction(loc, register))
                        }
                    }
                }
                val mustPush = info.stackSize % 2 == 1
                if (mustPush) {
                    instructions.add(PushInstruction(Register.r10()))
                }
                instructions.add(CallInstruction(expr.name))
                if (mustPush) {
                    instructions.add(PopInstruction(Register.r10()))
                }
                val tmp = info.addVariable(getUUID())
                instructions.add(PushInstruction(ImmediateVal(0)))
                instructions.add(MoveInstruction(Register.returnRegister(), tmp))
                tmp
            }
            is IRBinOpExpr -> {
                val leftLocation = traverse(expr.left)
                val rightLocation = traverse(expr.right)
                instructions.add(MoveInstruction(leftLocation, Register.r10()))
                instructions.add(MoveInstruction(rightLocation, Register.r11()))
                when (expr.op) {
                    BinOp.ADD -> {
                        instructions.add(AddInstruction(Register.r11(), Register.r10()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.SUBTRACT -> {
                        instructions.add(SubInstruction(Register.r11(), Register.r10()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MULTIPLY -> {
                        instructions.add(IMulInstruction(Register.r11(), Register.r10()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.DIVIDE -> {
                        instructions.add(MoveInstruction(Register.r10(), Register.rax()))
                        instructions.add(SignedExtendInstruction)
                        instructions.add(IDivInstruction(Register.r11()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.rax(), tmp))
                        tmp
                    }
                    BinOp.REMAINDER -> {
                        instructions.add(MoveInstruction(Register.r10(), Register.rax()))
                        instructions.add(SignedExtendInstruction)
                        instructions.add(IDivInstruction(Register.r11()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.rdx(), tmp))
                        tmp
                    }
                    BinOp.LESS -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETL, Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETG, Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.LESS_OR_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETLE, Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE_OR_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETGE, Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETE, Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.NOT_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETNE, Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.AND -> {
                        instructions.add(AndInstruction(Register.r10(), Register.r11()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                    BinOp.OR -> {
                        instructions.add(OrInstruction(Register.r10(), Register.r11()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                }
            }
            is IRUnaryOpExpr -> {
                val loc = traverse(expr.expr)
                when (expr.op) {
                    UnaryOp.MINUS -> {
                        instructions.add(MoveInstruction(loc, Register.r10()))
                        instructions.add(NegInstruction(Register.r10()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    UnaryOp.NOT -> {
                        instructions.add(MoveInstruction(loc, Register.r10()))
                        instructions.add(NotInstruction(Register.r10b()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                }
            }
            is IRLocationExpression -> {
                when (val location = expr.location) {
                    is IRIDLocation -> {
                        instructions.add(MoveInstruction(info.getVariableLocation(location.name), Register.r10()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    is IRArrayLocation -> {
                        val index = traverse(location.indexExpr)
                        instructions.add(MoveInstruction(index, Register.r10()))
                        val loc = ArrayAsm(location.name, Register.r10())
                        instructions.add(MoveInstruction(loc, Register.r10()))
                        val tmp = info.addVariable(getUUID())
                        instructions.add(PushInstruction(ImmediateVal(0)))
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                }
            }
        }
    }

    traverse(expr)
    return instructions
}

fun irStatementToLow(statement: IRStatement, info: AsmProgramInfo): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    when (statement) {
        is IRDirectAssignStatement -> {
            val exprInstructions = irExprToLow(statement.expr, info)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> info.getVariableLocation(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr, info)
                    instructions.addAll(indexInstructions)
                    val index = (indexInstructions.last() as MoveInstruction).dest
                    instructions.add(MoveInstruction(index, Register.r10()))
                    ArrayAsm(location.name, Register.r10())
                }
            }
            instructions.add(MoveInstruction(exprValLocation, Register.r10()))
            instructions.add(MoveInstruction(Register.r10(), rhs))
        }
        is IRIncrementStatement -> {
            val exprInstructions = irExprToLow(statement.expr, info)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> info.getVariableLocation(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr, info)
                    instructions.addAll(indexInstructions)
                    val index = (indexInstructions.last() as MoveInstruction).dest
                    instructions.add(MoveInstruction(index, Register.r10()))
                    ArrayAsm(location.name, Register.r10())
                }
            }
            instructions.add(MoveInstruction(rhs, Register.r10()))
            instructions.add(AddInstruction(exprValLocation, Register.r10()))
            instructions.add(MoveInstruction(Register.r10(), rhs))
        }
        is IRDecrementStatement -> {
            val exprInstructions = irExprToLow(statement.expr, info)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> info.getVariableLocation(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr, info)
                    instructions.addAll(indexInstructions)
                    val index = (indexInstructions.last() as MoveInstruction).dest
                    instructions.add(MoveInstruction(index, Register.r10()))
                    ArrayAsm(location.name, Register.r10())
                }
            }
            instructions.add(MoveInstruction(rhs, Register.r10()))
            instructions.add(SubInstruction(exprValLocation, Register.r10()))
            instructions.add(MoveInstruction(Register.r10(), rhs))
        }
        is IRInvokeStatement -> {
            val invokeInstructions = irExprToLow(statement.expr, info)
            instructions.addAll(invokeInstructions)
        }
        else -> throw IllegalStateException("shouldn't be calling for this statement: ${irToString(statement)}")
    }

    return instructions
}

fun irMethodToLow(method: IRMethodDeclaration, info: AsmProgramInfo): Method {
    val blocks = mutableListOf<Block>()
    blocks.add(Block(method.name, mutableListOf()))

    var loopStartLabel: String? = null
    var loopEndLabel: String? = null

    fun convert(ir: IR, block: Block): Block {
        return when (ir) {
            is IRStatement -> {
                when (ir) {
                    is IRAssignStatement, is IRInvokeStatement -> {
                        val instructions = irStatementToLow(ir, info)
                        block.instructions.addAll(instructions)
                        block
                    }
                    is IRBreakStatement -> {
                        block.instructions.add(JumpInstruction(AsmJumpOp.JMP, loopEndLabel!!))
                        block
                    }
                    is IRContinueStatement -> {
                        block.instructions.add(JumpInstruction(AsmJumpOp.JMP, loopStartLabel!!))
                        block
                    }
                    is IRIfStatement -> {
                        val conditionInstructions = irExprToLow(ir.condition, info)
                        block.instructions.addAll(conditionInstructions)
                        val ansLoc = (conditionInstructions.last() as MoveInstruction).dest
                        block.instructions.add(MoveInstruction(ansLoc, Register.r10()))
                        block.instructions.add(CmpInstruction(Register.r10(), ImmediateVal(1)))

                        info.ifCount++
                        val falseBlock = Block("False${info.ifCount}", mutableListOf())
                        val ifEndBlock = Block("IFend${info.ifCount}", mutableListOf())

                        block.instructions.add(JumpInstruction(AsmJumpOp.JNE, falseBlock.label!!))
                        val newBlock = convert(ir.ifBlock, block)
                        newBlock.instructions.add(JumpInstruction(AsmJumpOp.JMP, ifEndBlock.label!!))

                        blocks.add(falseBlock)
                        if (ir.elseBlock != null) {
                            convert(ir.elseBlock, falseBlock)
                        }

                        blocks.add(ifEndBlock)
                        ifEndBlock
                    }
                    is IRForStatement -> {
                        block.instructions.add(PushInstruction(ImmediateVal(0)))
                        val loopVarLoc = info.addVariable(ir.loopVar)
                        val initInstructions = irExprToLow(ir.initExpr, info)
                        block.instructions.addAll(initInstructions)
                        val initLoc = (initInstructions.last() as MoveInstruction).dest
                        block.instructions.add(MoveInstruction(initLoc, Register.r10()))
                        block.instructions.add(MoveInstruction(Register.r10(), loopVarLoc))

                        info.loopCount++
                        val loopBlock = Block("LoopStart${info.loopCount}", mutableListOf())
                        loopStartLabel = loopBlock.label
                        blocks.add(loopBlock)
                        val conditionInstructions = irExprToLow(
                            IRBinOpExpr(
                                IRLocationExpression(
                                    IRIDLocation(
                                        ir.loopVar,
                                        Position.unknown()
                                    ),
                                    Position.unknown()
                                ),
                                ir.condition,
                                BinOp.NOT_EQ,
                                Position.unknown()
                            ),
                            info
                        )
                        loopBlock.instructions.addAll(conditionInstructions)
                        val ansLoc = (conditionInstructions.last() as MoveInstruction).dest
                        loopBlock.instructions.add(MoveInstruction(ansLoc, Register.r10()))
                        loopBlock.instructions.add(CmpInstruction(Register.r10(), ImmediateVal(1)))

                        val loopEndBlock = Block("LoopEnd${info.loopCount}", mutableListOf())
                        loopEndLabel = loopEndBlock.label
                        loopBlock.instructions.add(JumpInstruction(AsmJumpOp.JNE, loopEndBlock.label!!))
                        val newBlock = convert(ir.body, loopBlock)
                        newBlock.instructions.add(JumpInstruction(AsmJumpOp.JMP, loopBlock.label!!))
                        blocks.add(loopEndBlock)
                        loopEndBlock
                    }
                    is IRReturnStatement -> {
                        block.instructions.add(LeaveInstruction)
                        block.instructions.add(ReturnInstruction)
                        block
                    }
                    is IRBlockStatement -> convert(ir.block, block)
                }
            }
            is IRBlock -> {
                info.enterScope()
                var currBlock = block
                for (memberDeclaration in ir.fieldDeclarations) {
                    currBlock.instructions.add(PushInstruction(ImmediateVal(0)))
                    info.addVariable(memberDeclaration.name)
                }
                for (statement in ir.statements) {
                    currBlock = convert(statement, currBlock)
                }
                val pops = info.leaveScope()
                for (i in 1..pops) {
                    currBlock.instructions.add(PopInstruction(Register.r10()))
                }
                currBlock
            }
            else -> throw IllegalStateException("cannot call this method on this IR")
        }
    }

    val argMap = mutableMapOf<String, Location>()
    for ((i, arg) in method.argList.withIndex()) {
        argMap[arg.name] = MemLoc(
            Register.basePointer(),
            NumberOffset((i + 2) * 8)
        )
    }
    info.pushMethodArgs(argMap)
    convert(method.block, blocks[0])
    info.popMethodArgs()

    return Method(argMap, blocks, method.name)
}

fun irProgramToLow(program: IRProgram): Program {
    val info = AsmProgramInfo()
    program.fieldDeclarations.forEach {
        when (it) {
            is IRRegularFieldDeclaration -> info.addGlobalVariable(it.name, it.type)
            is IRArrayFieldDeclaration -> info.addGlobalArray(it.name, it.size)
        }
    }
    val methods = program.methodDeclarations.map { irMethodToLow(it, info) }
    val globalVariables = program.fieldDeclarations.filterIsInstance<IRRegularFieldDeclaration>().map { it.name }
    val globalArrays = mutableMapOf<String, Int>()
    program.fieldDeclarations.filterIsInstance<IRArrayFieldDeclaration>().forEach { globalArrays[it.name] = it.size }

    return Program(globalVariables, globalArrays, info.globalText, methods)
}