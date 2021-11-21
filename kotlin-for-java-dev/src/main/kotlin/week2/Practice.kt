@file:JvmName("Util")

package week2

fun topLevel() {
    println("This is a top level function!")
}

fun main() {
    displaySeparator(character = '#', size = 5)

    iteratingOverMap()

    iteratingWithIndex()

    iteratingOverRange()

    for (ch in "abc") {
        print(ch + 1)
    }

    println(("Java".."Scala"))
}

/// function with default and named arguments
// we can use the annotation JVMOverloads to call a function with default arguments from Java
fun displaySeparator(character: Char = '*', size: Int = 10) {
    repeat(size) {
        print(character)
    }
    println()
}


///when without argument
fun updateWeather(degrees: Int) {
    val (description, colour) = when {
        degrees < 5 -> "cold" to Color.BLUE
        degrees < 23 -> "mild" to Color.ORANGE
        else -> "hot" to Color.RED
    }
}

enum class Color { BLUE, RED, ORANGE }

fun iteratingOverMap() {
    val map = mapOf(
        1 to "one",
        2 to "two",
        3 to "three"
    )
    for ((key, value) in map) {
        println("GOT KEY => [$key] WITH VALUE [$value]")
    }
}

fun iteratingWithIndex() {
    val lst = listOf(10, 20, 30, 40, 50)
    for ((index, item) in lst.withIndex()) {
        println(" [$index] -> [$item]")
    }
}

fun iteratingOverRange() {

    //including upper bound
    for (element in 1..20) {
        println("element -> $element")
    }

    //excluding upper bound
    for (element in 1 until 20) {
        println("element : $element")
    }
}