package deep.decaf.tester

import deep.decaf.ir.*
import deep.decaf.low.*
import deep.decaf.parser.*
import org.antlr.v4.runtime.*
import java.io.*

fun main() {
    val folder = File("/home/deep/work/compiler/cfg_test")
    val files = folder.listFiles()!!.filter { !it.isDirectory }.sorted()
    for (file in files) {
        val scanner = DecafLexer(CharStreams.fromFileName(file.absolutePath))
        val parser = DecafParser(CommonTokenStream(scanner))
        val irTree = parseTreeToIR(parser.program()) as IRProgram
        val errors = checkSemantics(irTree)
        if (errors.isNotEmpty())
            throw RuntimeException("Invalid program")
        val programCFG = constructCFG(irTree)
        val dotString = dotFileFromCFG(programCFG.mainCFG)
        println(dotString)
        val processBuilder = ProcessBuilder(
            listOf("dot", "-Tpdf", "-o", "${file.name}.pdf")
        )
        val outDir = File("/home/deep/work/test")
        processBuilder.directory(outDir)
        processBuilder.redirectError(File("${outDir.absolutePath}/${file.name}.log"))
        val process = processBuilder.start()
        val inputWriter = BufferedWriter(OutputStreamWriter(process.outputStream))
        inputWriter.write(dotString)
        inputWriter.flush()
        inputWriter.close()
    }
}