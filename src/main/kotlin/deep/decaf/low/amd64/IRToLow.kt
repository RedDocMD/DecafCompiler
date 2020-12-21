package deep.decaf.low.amd64

import deep.decaf.ir.*
import java.lang.IllegalStateException

fun irExprToLow(expr: IRExpr): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    fun traverse(expr: IRExpr): Location {
        return when (expr) {
            is IRIntLiteral -> {
                instructions.add(MoveInstruction(ImmediateVal(expr.lit), Register.r10()))
                val tmp = Label(getUUID())
                instructions.add(MoveInstruction(Register.r10(), tmp))
                tmp
            }
            is IRBoolLiteral -> {
                instructions.add(MoveInstruction(ImmediateVal(if (expr.lit) 1 else 0), Register.r10()))
                val tmp = Label(getUUID())
                instructions.add(MoveInstruction(Register.r10(), tmp))
                tmp
            }
            is IRMethodCallExpr -> {
                val argLocations = expr.argList.map { traverse(it) }
                argLocations.forEach { instructions.add(PushInstruction(it)) }
                instructions.add(CallInstruction(expr.name))
                for (i in 1..expr.argList.size) {
                    instructions.add(PopInstruction(Register.r10()))
                }
                Register.returnRegister()
            }
            is IRCallOutExpr -> TODO()
            is IRBinOpExpr -> {
                val leftLocation = traverse(expr.left)
                val rightLocation = traverse(expr.right)
                instructions.add(MoveInstruction(leftLocation, Register.r10()))
                instructions.add(MoveInstruction(rightLocation, Register.r11()))
                when (expr.op) {
                    BinOp.ADD -> {
                        instructions.add(AddInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.SUBTRACT -> {
                        instructions.add(SubInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MULTIPLY -> {
                        instructions.add(IMulInstruction(Register.r11(), Register.r10()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.DIVIDE -> {
                        instructions.add(MoveInstruction(Register.r10(), Register.rax()))
                        instructions.add(SignedExtendInstruction)
                        instructions.add(IDivInstruction(Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.rax(), tmp))
                        tmp
                    }
                    BinOp.REMAINDER -> {
                        instructions.add(MoveInstruction(Register.r10(), Register.rax()))
                        instructions.add(SignedExtendInstruction)
                        instructions.add(IDivInstruction(Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.rdx(), tmp))
                        tmp
                    }
                    BinOp.LESS -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETL, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETG, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.LESS_OR_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETLE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.MORE_OR_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETGE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.NOT_EQ -> {
                        instructions.add(CmpInstruction(Register.r11(), Register.r10()))
                        instructions.add(SetInstruction(SetType.SETNE, Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    BinOp.AND -> {
                        instructions.add(AndInstruction(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r11(), tmp))
                        tmp
                    }
                    BinOp.OR -> {
                        instructions.add(OrInstruction(Register.r10(), Register.r11()))
                        val tmp = Label(getUUID())
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
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                    UnaryOp.NOT -> {
                        instructions.add(MoveInstruction(loc, Register.r10()))
                        instructions.add(NotInstruction(Register.r10b()))
                        val tmp = Label(getUUID())
                        instructions.add(MoveInstruction(Register.r10(), tmp))
                        tmp
                    }
                }
            }
            is IRLocationExpression -> {
                when (val location = expr.location) {
                    is IRIDLocation -> Label(location.name)
                    is IRArrayLocation -> {
                        val index = traverse(location.indexExpr)
                        instructions.add(MoveInstruction(index, Register.r10()))
                        ArrayAsm(location.name, Register.r10())
                    }
                }
            }
        }
    }

    traverse(expr)
    return instructions
}

fun irStatementToLow(statement: IRStatement): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    when (statement) {
        is IRDirectAssignStatement -> {
            val exprInstructions = irExprToLow(statement.expr)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> Label(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr)
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
            val exprInstructions = irExprToLow(statement.expr)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> Label(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr)
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
            val exprInstructions = irExprToLow(statement.expr)
            instructions.addAll(exprInstructions)
            val exprValLocation = (exprInstructions.last() as MoveInstruction).dest
            val rhs = when (val location = statement.location) {
                is IRIDLocation -> Label(location.name)
                is IRArrayLocation -> {
                    val indexInstructions = irExprToLow(location.indexExpr)
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
            val invokeInstructions = irExprToLow(statement.expr)
            instructions.addAll(invokeInstructions)
        }
        else -> throw IllegalStateException("shouldn't be calling for this statement: ${irToString(statement)}")
    }

    return instructions
}

fun irMethodToLow(method: IRMethodDeclaration): List<Block> {
    val blocks = mutableListOf<Block>()
    blocks.add(Block(method.name, mutableListOf()))

    var falseBlockCount = 0
    var ifEndCount = 0
    var loopStartCount = 0
    var loopEndCount = 0

    fun convert(ir: IR, block: Block): Block {
        return when (ir) {
            is IRStatement -> {
                when (ir) {
                    is IRAssignStatement, is IRInvokeStatement -> {
                        val instructions = irStatementToLow(ir)
                        block.instructions.addAll(instructions)
                        block
                    }
                    is IRBreakStatement -> TODO()
                    is IRContinueStatement -> TODO()
                    is IRIfStatement -> {
                        val conditionInstructions = irExprToLow(ir.condition)
                        block.instructions.addAll(conditionInstructions)
                        val ansLoc = (conditionInstructions.last() as MoveInstruction).dest
                        block.instructions.add(MoveInstruction(ansLoc, Register.r10()))
                        block.instructions.add(CmpInstruction(Register.r10(), ImmediateVal(1)))

                        falseBlockCount++
                        ifEndCount++
                        val falseBlock = Block("False$falseBlockCount", mutableListOf())
                        val ifEndBlock = Block("IFend$ifEndCount", mutableListOf())

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
                        val initInstructions = irExprToLow(ir.initExpr)
                        block.instructions.addAll(initInstructions)
                        val initLoc = (initInstructions.last() as MoveInstruction).dest
                        block.instructions.add(MoveInstruction(initLoc, Register.r10()))
                        block.instructions.add(MoveInstruction(Register.r10(), Label(ir.loopVar)))

                        loopStartCount++
                        val loopBlock = Block("LoopStart$loopStartCount", mutableListOf())
                        blocks.add(loopBlock)
                        val conditionInstructions = irExprToLow(ir.condition)
                        loopBlock.instructions.addAll(conditionInstructions)
                        val ansLoc = (conditionInstructions.last() as MoveInstruction).dest
                        loopBlock.instructions.add(MoveInstruction(ansLoc, Register.r10()))
                        loopBlock.instructions.add(CmpInstruction(Register.r10(), ImmediateVal(1)))

                        loopEndCount++
                        val loopEndBlock = Block("LoopEnd$loopEndCount", mutableListOf())
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
                var currBlock = block
                for (memberDeclaration in ir.fieldDeclarations) {
                    currBlock.instructions.add(PushInstruction(ImmediateVal(0)))
                }
                for (statement in ir.statements) {
                    currBlock = convert(statement, currBlock)
                }
                currBlock
            }
            else -> throw IllegalStateException("cannot call this method on this IR")
        }
    }

    blocks[0].instructions.add(EnterInstruction(0))
    convert(method.block, blocks[0])
    blocks.last().instructions.add(LeaveInstruction)
    blocks.last().instructions.add(ReturnInstruction)

    return blocks
}