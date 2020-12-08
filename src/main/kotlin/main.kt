import java.io.File
import java.io.FileInputStream
import java.util.*

fun readLines(path: String): List<String> {
    val lines = mutableListOf<String>()
    val input = Scanner(FileInputStream(File(path).also { println("name = ${it.absolutePath}") }))
    while (input.hasNextLine()) {
        val num = input.nextLine()
        if (num.isEmpty())
            break
        lines.add(num)
    }
    input.close()
    return lines.toList()
}

fun Int.isIn(from: Int, to: Int) = this in from .. to

fun List<Int>.mul() =  this.reduce(Int::times)

fun readLinesMergeEmpty(path: String, merge: String = " "): List<String> {
    val lines = mutableListOf<String>()
    val buffer = mutableListOf<String>()
    val input = Scanner(FileInputStream(File(path).also { println("name = ${it.absolutePath}") }))
    while (input.hasNextLine()) {
        val line = input.nextLine().trim()
        if (line.isEmpty()){
            buffer.reduce { acc, value ->   acc + value }.trim().also { lines.add(it) }
            buffer.clear()
        }else {
            buffer.add(line + merge)
        }

    }
    input.close()
    return lines.toList()
}