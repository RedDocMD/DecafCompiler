package deep.decaf.tester

import deep.decaf.ir.*
import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import java.io.*

fun main(args: Array<String>) {
    val exampleDirName = "/home/deep/work/compiler/p2files/semantics"
//    val exampleDirName = "/home/deep/work/compiler/provided1/parser"
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
            var hasErrors = false
            parser.addErrorListener(object : BaseErrorListener() {
                override fun syntaxError(
                    recognizer: Recognizer<*, *>?,
                    offendingSymbol: Any?,
                    line: Int,
                    charPositionInLine: Int,
                    msg: String?,
                    e: RecognitionException?
                ) {
                    val errMsg = "$line:$charPositionInLine $msg"
                    println(errMsg)
                    hasErrors = true
                }
            })
            val parseTree = parser.program()
            if (!hasErrors) {
                val irTree = parseTreeToIR(parseTree) as IRProgram
                val errors = checkSemantics(irTree)
                if (errors.isEmpty()) {
                    println("All checks passed!")
                } else {
                    println(irToString(irTree, 0))
                    errors.forEach { println(it) }
                }
            }
        }
    }
}
