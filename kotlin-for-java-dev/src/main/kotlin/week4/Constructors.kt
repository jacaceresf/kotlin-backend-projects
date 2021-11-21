package week4

import java.time.LocalDate

class Person(val name: String, val birthDate: LocalDate) {
    val age: Int = LocalDate.now().year - this.birthDate.year
}

fun main() {

    val person = Person("George", LocalDate.of(1997, 9, 3))

    println(person.age)
}