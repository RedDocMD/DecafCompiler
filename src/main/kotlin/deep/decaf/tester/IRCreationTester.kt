package deep.decaf.tester

import deep.decaf.ir.*
import deep.decaf.parser.*
import org.antlr.v4.runtime.*

fun main(args: Array<String>) {
//    val filename = "/home/deep/work/compiler/provided1/parser/legal-18"
    val filename = "/home/deep/work/compiler/provided1/cond-expr.decaf"
    val lexer = DecafLexer(CharStreams.fromFileName(filename))
    val parser = DecafParser(CommonTokenStream(lexer))
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
            hasErrors = true
            val errMsg = "line $line:$charPositionInLine $msg"
            println(errMsg)
        }
    })
    if (!hasErrors) {
        val programTree = parser.program()
        val ir = parseTreeToIR(programTree)
        println(irToString(ir))
    }
}