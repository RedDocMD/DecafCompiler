package deep.decaf.main

import deep.decaf.ir.*
import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import picocli.*
import picocli.CommandLine.*;
import java.io.*
import java.util.concurrent.*
import kotlin.system.*

@Command(
    name = "Decaf compiler", mixinStandardHelpOptions = true, version = ["Decaf compiler 1.0"],
    description = ["Compiles source files written in the Decaf language"]
)
class Compiler : Callable<Int> {

    @Parameters(index = "0", description = ["The file to compile"])
    lateinit var file: File

    @ArgGroup(exclusive = true, multiplicity = "1")
    lateinit var stage: Stage

    class Stage {
        @Option(names = ["-s", "--scan"], description = ["Only scan for tokens"])
        var scan = false

        @Option(names = ["-p", "--parse"], description = ["Only scan and then parse"])
        var parse = false

        @Option(names = ["-c", "--semantic"], description = ["Scan, parse and then perform semantic checks"])
        var check = false
    }

    @Option(names = ["-g", "--debug"], description = ["Enable debug mode"])
    var debug = false

    override fun call(): Int {
        if (!file.exists()) {
            val msg = Help.Ansi.AUTO.string("@|bold,red ${file.absolutePath} does not exist|@")
            println(msg)
            return 1
        }
        return when {
            stage.scan -> scanFile(makeLexer(file), debug)
            stage.parse -> {
                val lexer = makeLexer(file)
                val exitCode = scanFile(lexer, debug)
                if (exitCode == 0) {
                    val parser = makeParser(lexer)
                    val (newExitCode, _) = parseFile(parser)
                    newExitCode
                } else exitCode
            }
            stage.check -> {
                val lexer = makeLexer(file)
                val exitCode = scanFile(lexer, debug)
                if (exitCode == 0) {
                    val parser = makeParser(lexer)
                    val (newExitCode, tree) = parseFile(parser)
                    if (newExitCode == 0) {
                        val ir = makeIR(tree)
                        semanticCheck(ir, debug)
                    } else newExitCode
                } else exitCode
            }
            else -> 0
        }
    }
}

fun main(args: Array<String>): Unit = exitProcess(CommandLine(Compiler()).execute(*args))

fun scanFile(lexer: DecafLexer, debug: Boolean): Int {
    var hasErrors = false
    lexer.removeErrorListeners()
    lexer.addErrorListener(object : BaseErrorListener() {
        override fun syntaxError(
            recognizer: Recognizer<*, *>?,
            offendingSymbol: Any?,
            line: Int,
            charPositionInLine: Int,
            msg: String?,
            e: RecognitionException?
        ) {
            hasErrors = true
            val errMsg = "$line:$charPositionInLine $msg"
            println(errMsg)
        }
    })
    if (debug) {
        var token: Token
        token = lexer.nextToken()
        while (token.type != Token.EOF) {
            println("${token.line} ${lexer.vocabulary.getSymbolicName(token.type)}  ${token.text}")
            token = lexer.nextToken()
        }
    }
    return if (hasErrors) 1 else 0
}

fun makeLexer(file: File): DecafLexer = DecafLexer(CharStreams.fromFileName(file.absolutePath))

fun makeParser(lexer: DecafLexer): DecafParser = DecafParser(CommonTokenStream(lexer))

fun parseFile(parser: DecafParser): Pair<Int, DecafParser.ProgramContext> {
    var hasErrors = false
    parser.removeErrorListeners()
    parser.addErrorListener(object : BaseErrorListener() {
        override fun syntaxError(
            recognizer: Recognizer<*, *>?,
            offendingSymbol: Any?,
            line: Int,
            charPositionInLine: Int,
            msg: String?,
            e: RecognitionException?
        ) {
            hasErrors = true
            val errMsg = "$line:$charPositionInLine $msg"
            println(errMsg)
        }
    })
    val program = parser.program()
    return if (hasErrors) Pair(1, program) else Pair(0, program)
}

fun makeIR(tree: DecafParser.ProgramContext): IRProgram = parseTreeToIR(tree) as IRProgram

fun semanticCheck(program: IRProgram, debug: Boolean): Int {
    val errors = checkSemantics(program)
    if (debug) println(irToString(program, 0))
    return if (errors.isEmpty()) 0
    else {
        errors.forEach { println(it) }
        1
    }
}