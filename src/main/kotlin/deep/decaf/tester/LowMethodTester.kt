package deep.decaf.tester

import deep.decaf.ir.*
import deep.decaf.low.amd64.*
import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import java.io.*

fun main() {
    val folder = File("/home/deep/work/compiler/p3files/codegen")
    val files = folder.listFiles()!!.filter { !it.isDirectory }.sorted()
    for (file in files) {
        val scanner = DecafLexer(CharStreams.fromFileName(file.absolutePath))
        val parser = DecafParser(CommonTokenStream(scanner))
        val irTree = parseTreeToIR(parser.program()) as IRProgram
        val errors = checkSemantics(irTree)
        if (errors.isNotEmpty())
            throw RuntimeException("Invalid program")
        println(file.absolutePath)
        val program = irProgramToLow(irTree)
        println(program)
    }
}