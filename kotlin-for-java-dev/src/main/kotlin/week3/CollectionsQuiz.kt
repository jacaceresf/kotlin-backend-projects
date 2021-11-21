package week3

fun main() {

    val heroes = listOf(
        Hero("The Captain", 45, Gender.MALE),
        Hero("The Captain", 3, Gender.MALE),
        Hero("The Kid", 9, null),
        Hero("Lady Lauren", 29, Gender.FEMALE),
        Hero("First Mate", 29, Gender.MALE),
        Hero("Sir Stephen", 37, Gender.MALE)
    )

    val mapByAge: Map<Int, List<Hero>> =
        heroes.groupBy { it.age }

    val (age, group) = mapByAge.maxByOrNull { (_, group) ->
        group.size
    }!!
    println(age)


    val mapByName = heroes.associateBy { it.name }
    println(mapByName)
    val unknownHero = Hero("Unknown", 0, null)
    mapByName.getOrElse("unknown") { unknownHero }.age

    val (first, second) = heroes
        .flatMap { heroes.map { hero -> it to hero } }
        .maxByOrNull { it.first.age - it.second.age }!!

    println(first.name)
}

