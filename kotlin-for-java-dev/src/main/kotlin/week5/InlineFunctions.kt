package week5

///the compiler substitutes a body of the function instead of calling it

inline fun <R> run(block: () -> R): R = block()

fun main() {
    val name = "Kotlin"

    run { println("I'm learning $name") }
}