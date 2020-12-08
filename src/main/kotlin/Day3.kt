
fun day3Part1(right: Int = 3, down: Int = 1) {
    val entries = readLines("data/day3.csv").map { it.trim().toCharArray() }
    var encounter = 0
    var pos = 0
    for (n in entries){
        if(n[pos] == '#')
            encounter++
        pos += right
        if(pos >= n.size)
            pos %= n.size
    }

    println("Enounter = $encounter")
}


fun day3Part2() {
    val entries = readLines("data/day3.csv").map { it.trim().toCharArray() }
    var encounterArray = mutableListOf<Int>()
    val xIncrements = listOf(1,3,5,7)
    var xAxis = 0
    var yAxis = 0
    var xIncrementPos = 0
    var yIncrement = 1

    while (yAxis < entries.size){
        println(entries[yAxis])
        if(entries[yAxis][xAxis] == '#')
            entries[yAxis].toList().subList( 0,xAxis + 1).count { it == '#' }.let { encounterArray.add(it) }
        xAxis += xIncrements[xIncrementPos]
        xIncrementPos++
        if(xAxis >= entries[yAxis].size)
            xAxis %= entries[yAxis].size

        yAxis += yIncrement
        if(xIncrementPos >= xIncrements.size){
            yIncrement++
            xIncrementPos = 0
        }
        println("$xAxis,$yAxis ")

    }

    println("Enounter = ${encounterArray} ${encounterArray.mul()}")
}

fun main(){
    day3Part2()
}