package dev.jacaceresf.kmongo.services

import dev.jacaceresf.kmongo.logging.getLogger
import dev.jacaceresf.kmongo.models.Person
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class PersonService {

    private val log = getLogger()

    /**
     * It creates a mongo instance using the default port 27017
     */
    private val client = KMongo.createClient()

    /**
     * Obtains the database instance. If it doesn't exist, it will be created at the first insert.
     */
    private val database = client.getDatabase("person")

    /**
     * Gets a collection to perform all operations over it.
     */
    private val personCollection = database.getCollection<Person>()

    fun create(person: Person): Id<Person>? {
        log.info("Going to add person => {$person}")
        personCollection.insertOne(person)
        return person.id
    }

    fun findAll(): List<Person> {
        val result = personCollection.find().toList()
        log.info("Going to return a list with [${result.size}] records")
        return result
    }

    fun findById(id: String): Person? {
        val bsonId: Id<Person> = ObjectId(id).toId()
        return personCollection.findOne(Person::id eq bsonId)
    }

    fun findByName(name: String): List<Person> {
        val nameFilter = Person::name regex name /// case sensitive filter
        return personCollection.find(nameFilter).toList()
    }

    fun findByNameAndAgeExactly(name: String, age: Int): List<Person> {
        log.info("Looking exactly for name=[$name] and age=[$age]")
        /// when we pass multiple filters to the find method, they are implicitly combined with the AND operator
        return personCollection.find(
            Person::name eq name, Person::age eq age
        ).toList()
    }

    fun findByNameAndAge(name: String, age: Int): List<Person> {
        return personCollection.find(
            or(Person::name eq name, Person::age gte age)
        ).toList()
    }
}