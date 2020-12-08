import java.awt.Color

/*


    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
        If cm, the number must be at least 150 and at most 193.
        If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.


 */

data class Day4(val byr: Int, val iyr:Int, val eyr: Int, val hgt: Int, val hgtType: String, val hcl: String,val ecl: String,val pid: String,val hasAll: Boolean )
private val INVALID = Day4(0,0,0,0,"", "","","",false)

private fun toDay4Data(rowList: List<String>): Day4 {
   /* if( rowList.contains("byr") &&
        rowList.contains("iyr") &&
        rowList.contains("eyr") &&
        rowList.contains("hgt" ) &&
        rowList.contains("hcl") &&
        rowList.contains("ecl") &&
        rowList.contains("pid")) {
        return INVALID
    }*/

    var data = INVALID.copy( hasAll = true)



    rowList.onEach { cell ->
        val (type,value) = cell.trim().split(":")

        if(type.contains("byr")){
            if(value.toInt().isIn(1920,2002)){
                data = data.copy(byr = value.toInt())
            }else{
                return INVALID
            }
        }

        if(type.contains("iyr")){
            if(value.toInt().isIn(2010,2020)){
                data = data.copy(iyr = value.toInt())
            }else{
                return INVALID
            }
        }

        if(type.contains("eyr")){
            if(value.toInt().isIn(2020,2030)){
                data = data.copy(eyr = value.toInt())
            }else{
                return INVALID
            }
        }

        if(type.contains("hcl")){
            if(value.startsWith("#") && value.replace("#","").toIntOrNull(16) != null && value.replace("#","").length == 6)
                data = data.copy(hcl = value.replace("#",""))
            else {
                return INVALID
            }
        }

        if(type.contains("ecl")){
            //amb blu brn gry grn hzl oth
            if(value == "amb" || value == ("blu") || value == ("brn") || value == ("gry")
                || value == ("grn") || value == ("hzl") || value == ("oth"))
                    data = data.copy(ecl = value)
            else {
                return INVALID
            }
        }

        if(type.contains("pid")){
            if(value.startsWith("0") && value.length == 9)
               data =  data.copy(pid = value)
            else {
                return INVALID
            }
        }


        if(type.contains("hgt")){  // If in, the number must be at least 59 and at most 76.
            if(value.endsWith("cm") && value.replace("cm","").toInt().isIn(150,193))
                data = data.copy(hgt = 150,hgtType = "cm")
            else if(value.endsWith("in") && value.replace("in","").toInt().isIn(59,76))
                data = data.copy(hgt = 59,hgtType = "in")
            else {
                println("$rowList")
                return INVALID
            }
        }


    }

    return data
}


fun day4Part2(){
    val entries = readLinesMergeEmpty("data/day4.csv", " ").map {
        if( it.contains("byr") &&
            it.contains("iyr") &&
            it.contains("eyr") &&
            it.contains("hgt" ) &&
            it.contains("hcl") &&
            it.contains("ecl") &&
            it.contains("pid")) {
            toDay4Data(it.split(" "))
        }
        else {
            INVALID
        }
    }.count {  if(it.hasAll) println(it)
        it.hasAll }.also { println("RESULT + $it") }
}


fun day4Part1(){
    val entries = readLinesMergeEmpty("data/day4.csv", " ").map { it.split(" ").map { it.split(":") } }
    var validEntries = 0
    entries.onEach {
        println(it)
        if( it[0].contains("byr") &&
                it[0].contains("iyr") &&
                    it[0].contains("eyr") &&
                        it[0].contains("hgt" ) &&
                            it[0].contains("hcl") &&
                                it[0].contains("ecl") &&
                                    it[0].contains("pid")) {
            validEntries++
        }
    }

    println("Valid Entries $validEntries")
}


fun main(){
    day4Part2()
}