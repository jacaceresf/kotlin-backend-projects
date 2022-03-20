package dev.jacaceresf.kmongo.plugins

import dev.jacaceresf.kmongo.extensions.toDto
import dev.jacaceresf.kmongo.extensions.toPerson
import dev.jacaceresf.kmongo.handler.ErrorResponse
import dev.jacaceresf.kmongo.models.Person
import dev.jacaceresf.kmongo.models.PersonDto
import dev.jacaceresf.kmongo.services.PersonService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Application.configureRouting() {

    val personService = PersonService()

    routing {
        route("/person") {
            post {
                val request = call.receive<PersonDto>()
                val person = request.toPerson()

                personService.create(person)?.let { personId ->
                    call.response.headers.append("User-Id-Header", personId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            }

            get {
                val peopleList = personService.findAll().map(Person::toDto)
                call.respond(peopleList)
            }

            get("/{id}") {
                val id = call.parameters["id"].toString()

                personService.findById(id)?.let { person -> call.respond(person.toDto()) }
                    ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
            }

            get("/search") {
                val name = call.request.queryParameters["name"]?.toString()
                val age = call.request.queryParameters["age"]?.toString()

                if (Objects.isNull(name)) {
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
                }

                val result = if (Objects.isNull(age)) {
                    personService.findByNameAndAge(name = name!!, age = age!!.toInt()).map(Person::toDto)
                } else {
                    personService.findByNameAndAgeExactly(name = name!!, age = age!!.toInt()).map(Person::toDto)
                }

                call.respond(result)
            }
        }
    }
}
