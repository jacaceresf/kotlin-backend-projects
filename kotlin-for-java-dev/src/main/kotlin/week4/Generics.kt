package week4

/// declare as <T: Any> for a non-nullable upper bound
fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T> {

    val destination = ArrayList<T>()
    for (element in this) {
        if (predicate(element)) destination.add(element)
    }

    return destination
}

fun main() {

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
    val strings = listOf("", "hello", "", "hola", "hi")

    println(numbers.filter { it > 2 })
    println(strings.filter { it.isNotEmpty() })
}