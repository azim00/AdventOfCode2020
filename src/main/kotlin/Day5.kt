

private fun decoder(lowerLimit: Int, upperLimit: Int, charArray: List<Char>,lowerChar: Char,upperChar: Char): Int{
    //println(charArray + "  $lowerLimit   $upperLimit")
    return if(charArray.size == 1){
        if(charArray[0] == lowerChar) lowerLimit else upperLimit
    }else
    {
        val mid = (lowerLimit + upperLimit)/2
        if(charArray[0] == upperChar)
            decoder(mid + 1,upperLimit,charArray.drop(1),lowerChar,upperChar)
        else
            decoder(lowerLimit,mid,charArray.drop(1),lowerChar,upperChar)
    }
}


private fun calculatePass(pass: String) = (decoder(0,127,pass.subSequence(0,7).toList(),'F','B')*8)+
            decoder(0,7,pass.substring(7,pass.length).toList(),'L','R')




fun Day5(){
    readLines("data/day5.csv")
        .map { calculatePass(it) }
        .maxOrNull()
        .also {
            println(it)
        }
}



fun Day5Part2(){
    (0 .. 832).minus(readLines("data/day5.csv").map { calculatePass(it) })
        .onEach {
            println(it)
        }
}

fun main(){
    Day5Part2()
}