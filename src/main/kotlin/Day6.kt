


private fun Day6(){
    readLinesMergeEmpty("data/day6.csv")
        .asSequence()
        .map { it.toLowerCase().toList() }
        .map { it.distinct() }
        .map { it.filter { c -> c  in ('a' .. 'z')} }
        .map { it.size }
        .sum()
        .also { println(it) }
}

private fun Day6Part2(){
    readLinesMergeEmpty("data/day6.csv")
        .asSequence()
        .map { it.toLowerCase().split(" ").map { o ->  o.toList() } }
        .map {
            val first = it[0].toMutableList()
            for (n in it){
                first.retainAll(n)
            }
            first
        }
        .map { it.distinct() }
        .map { it.filter { c -> c  in ('a' .. 'z')} }
        .map { it.size }
        .sum()
        .also { println(it) }
}


fun main(){
    Day6Part2()
}