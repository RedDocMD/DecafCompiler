package deep.decaf.main

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

    @Option(names = ["-s", "--scan"], description = ["Only scan for tokens"])
    var scan = false

    @Option(names = ["-p", "--parse"], description = ["Only scan and then parse"])
    var parse = false

    override fun call(): Int {
        if (!file.exists()) {
            val msg = Help.Ansi.AUTO.string("@|bold,red ${file.absolutePath} does not exist|@")
            println(msg)
            return 1
        }
        when {
            scan -> return scanFile(file)
            parse -> return parseFile(file)
        }
        return 0
    }
}

fun main(args: Array<String>): Unit = exitProcess(CommandLine(Compiler()).execute(*args))

fun scanFile(file: File): Int {
    val lexer = makeLexer(file)
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
            val errMsg = "line $line:$charPositionInLine $msg"
            println(errMsg)
        }
    })
    var token: Token
    token = lexer.nextToken()
    while (token.type != Token.EOF) {
        println("${token.line} ${lexer.vocabulary.getSymbolicName(token.type)}  ${token.text}")
        token = lexer.nextToken()
    }
    return if (hasErrors) 1 else 0
}

fun makeLexer(file: File): DecafLexer = DecafLexer(CharStreams.fromFileName(file.absolutePath))

fun makeParser(lexer: DecafLexer): DecafParser = DecafParser(CommonTokenStream(lexer))

fun parseFile(file: File): Int {
    val lexer = makeLexer(file)
    val parser = makeParser(lexer)
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
            val errMsg = "line $line:$charPositionInLine $msg"
            println(errMsg)
        }
    })
    parser.program()
    return if (hasErrors) 1 else 0
}
