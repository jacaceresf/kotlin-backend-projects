package week4

data class Point(val x: Int, val y: Int)

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

operator fun Point.times(scale: Int): Point {
    return Point(x * scale, y * scale)
}

/// - minus
/// * times
/// / div
/// % mod

fun main() {

    val p1 = Point(1, 2)
    val p2 = Point(p1.x * 5, p1.y * 5)

    println(p1 + p2)

    println(p2 * 5)
}