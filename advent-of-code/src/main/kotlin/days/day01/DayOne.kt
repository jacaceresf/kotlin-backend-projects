package days

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

fun findTwoNumbers(numbers: List<String>) {
    for ((index, number) in numbers.withIndex().withIndex()) {

        val value = Integer.parseInt(number.value)
        val numbersWithoutCurrentIndex = numbers.subList(index, numbers.size - 1)

        for (n in numbersWithoutCurrentIndex) {
            val sum = value + Integer.parseInt(n)
            if (sum == 2020) {
                println("Got value [$value] and current iteration value [$n] at index [$index]")
                println("Result -> ${value * Integer.parseInt(n)}")
            }
        }
    }
}

fun findThreeNumbers(numbers: List<String>) {

    for ((index, number) in numbers.withIndex().withIndex()) {

        val value = Integer.parseInt(number.value)
        val numbersWithoutCurrentIndex = numbers.subList(index, numbers.size - 1)

        for ((_index, _number) in numbersWithoutCurrentIndex.withIndex().iterator()) {

            val _numbersWithoutCurrentIndex =
                numbersWithoutCurrentIndex.subList(_index, numbersWithoutCurrentIndex.size - 1)
            val _value = Integer.parseInt(_number)

            for (n in _numbersWithoutCurrentIndex) {
                val sum = value + _value + Integer.parseInt(n)
                if (sum == 2020) {
                    println("1 - Index [$index] Number [$value]")
                    println("2 - Index [$_index] Number [$_value]")
                    println("3 - Number [$n]")
                    println("Result -> ${value * _value * Integer.parseInt(n)}")
                }
            }
        }
    }
}

fun main() {
    val numbers = File("src/main/kotlin/days/day01/input.txt").readText().trim().split("\n")
    findThreeNumbers(numbers)
}