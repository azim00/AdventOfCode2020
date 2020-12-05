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