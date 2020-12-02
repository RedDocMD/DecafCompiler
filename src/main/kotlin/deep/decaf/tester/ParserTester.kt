package deep.decaf

import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import java.io.*

fun main(args: Array<String>) {
    val exampleDirName = "/home/deep/work/compiler/provided1/parser"
    val exampleDir = File(exampleDirName)
    val fileNames = exampleDir.listFiles()?.filterNot { it.isDirectory }?.map { it.absolutePath }?.sorted()
    if (fileNames != null) {
        for (fileName in fileNames) {
            println("\n$fileName")
            val lexer = DecafLexer(CharStreams.fromFileName(fileName))
            lexer.removeErrorListeners()
            val stream = CommonTokenStream(lexer)
            val parser = DecafParser(stream)
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
                    val errMsg = "line $line:$charPositionInLine $msg"
                    println(errMsg)
                }
            })
            parser.program()
        }
    }
}
