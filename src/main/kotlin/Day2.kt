data class PasswordFormat(val lowerLimit: Int, val upperLimit: Int, val char: Char, val password: CharArray)

private fun String.toPasswordFormat(): PasswordFormat {
    val colon = this.split(":").map { it.trim() }
    val space = colon[0].split(" ").map { it.trim() }
    val minus = space[0].split("-").map { it.trim().toInt() }
    return PasswordFormat(minus[0], minus[1], space[1].toCharArray()[0], colon[1].toCharArray())
}

private fun PasswordFormat.hasAtPosition(pos: Int) = password[pos] == char

private fun countBool(vararg bool: Boolean) = bool.count{ it }.also { println("Count = $it") }


fun day2() {
    val entries = readLines("data/day2.csv").map { it.toPasswordFormat() }
    val validEntries = entries.count { passwordFormat ->
        passwordFormat.password.count { it  == passwordFormat.char }
            .isIn(passwordFormat.lowerLimit,passwordFormat.upperLimit)
    }

    println("Valid entries = $validEntries")
}

fun day2Part2(){
    val entries = readLines("data/day2.csv").map { it.toPasswordFormat() }.map { it.copy(
        lowerLimit = it.lowerLimit - 1,
        upperLimit = it.upperLimit -1
    ) }
   val validEntries = entries.count {
        countBool(it.hasAtPosition(it.lowerLimit),it.hasAtPosition(it.upperLimit)) == 1
    }

    println("Valid Entries = $validEntries")
}



fun main() {
    day2Part2()
}