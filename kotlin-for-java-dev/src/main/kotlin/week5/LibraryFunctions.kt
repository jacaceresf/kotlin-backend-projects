package week5

fun main() {
    val number = 28

    val takeIf = number.takeIf { it % 2 == 0 }.also {
        if (it != null) it > 30
    }

    val takeUnless = number.takeUnless { it < 50 }
    println(takeIf)
    println(takeUnless)

    val list = listOf("Bucks", "Cavaliers", "Wizards", "Warriors", "Rockets", "Nets", "Suns")

    list.filter { it.startsWith("W") }
        .takeIf { it.size > 1 }
        .let { println(it) }

    ///repeat an action for a given number of times
    repeat(3) {
        println("repeating!")
    }
}