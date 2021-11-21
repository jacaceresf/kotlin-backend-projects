package days.day02

import java.io.File

fun checkPasswordOccurrencePolicy(groupOfPasswords: List<String>) {
    var count = 0
    for (password in groupOfPasswords) {

        val split = password.split(": ")
        val passValue = split[1]

        val policy = split[0].split(" ")
        val minimum = Integer.parseInt(policy[0].split("-")[0])
        val maximum = Integer.parseInt(policy[0].split("-")[1])

        val letter = policy[1]

        val letterCount = passValue.count { it.toString() == letter }

        //use range
        if (letterCount in minimum..maximum) {
            println("Pass value [$passValue] contains [$letter] at least $minimum .. $maximum")
            count++
        }
    }

    println("[$count] passwords are valid according to the policy.")
}

fun rightPolicy(groupOfPasswords: List<String>): Int {

    var count = 0
    for (password in groupOfPasswords) {

        val split = password.split(": ")
        val passValue = split[1]

        val policy = split[0].split(" ")
        val firstIndex = Integer.parseInt(policy[0].split("-")[0]) - 1
        val secondIndex = Integer.parseInt(policy[0].split("-")[1]) - 1

        val letter = policy[1]

        println("Got pass value $passValue")
        println("Index [$firstIndex - $secondIndex] -> $letter")

        if (passValue.length >= secondIndex) {
            if (passValue[firstIndex].toString() == letter) {
                if (passValue[secondIndex].toString() != letter) {
                    println("OK!")
                    count++
                }
            } else if (passValue[secondIndex].toString() == letter) {
                println("OK1!")
                count++
            }
        }
    }
    return count
}

fun main() {

    val groupOfPasswords = File("src/main/kotlin/days/day02/input.txt").readText().trim().split("\n")

    println(groupOfPasswords)

    val result = rightPolicy(groupOfPasswords)

    print("Result is => $result")

}