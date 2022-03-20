package dev.jacaceresf.kmongo.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Person(
    @BsonId
    val id: Id<Person>? = null, /// by making it null as default, the ID will be generated automatically.
    val name: String,
    val age: Int
)

@Serializable
data class PersonDto(
    val id: String? = null,
    val name: String,
    val age: Int
)
