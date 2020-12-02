package deep.decaf

import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import java.io.*

fun main(args: Array<String>) {
    val exampleDirName = "/home/deep/work/compiler/provided1/scanner"
    val exampleDir = File(exampleDirName)
    val fileNames = exampleDir.listFiles()?.filterNot { it.isDirectory }?.map { it.absolutePath }?.sorted()
    if (fileNames != null) {
        for (fileName in fileNames) {
            println("\n$fileName")
            val lexer = DecafLexer(CharStreams.fromFileName(fileName))
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
        }
    }
}
