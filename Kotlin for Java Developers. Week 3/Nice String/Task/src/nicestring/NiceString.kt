package nicestring

val substrings = listOf("bu", "ba", "be")
val vowels = listOf('a', 'e', 'i', 'o', 'u')

//val vowelStr = listOf("a", "e", "i", "o", "u")
fun String.isNice(): Boolean {

    var satisfiedCondition = 0

    val any = substrings.any { it in this }

    if (!any) {
        satisfiedCondition++
    }

    val count = this.filter { it in vowels }.count()
    if (count >= 3) {
        satisfiedCondition++
    }

    val pairs = this.zipWithNext()

    if (pairs.any { it.first == it.second })
        satisfiedCondition++


    return satisfiedCondition >= 2
}