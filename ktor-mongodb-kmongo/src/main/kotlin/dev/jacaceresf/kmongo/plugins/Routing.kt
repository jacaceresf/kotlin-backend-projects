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

    routing {
        route("/person") {
            getPeopleList()
            getPersonById()
            getPersonBySearchTerm()
            savePerson()
            updatePerson()
            deletePersonById()
        }
    }
}


fun Route.getPeopleList() {
    get {
        val peopleList = PersonService.findAll().map(Person::toDto)
        call.respond(peopleList)
    }
}

fun Route.deletePersonById() {

    delete("/{id}") {
        val id = call.parameters["id"].toString()

        val delete = PersonService.deleteById(id)
        if (delete) {
            call.respond(HttpStatusCode.NoContent)
        }

        call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
    }
}

fun Route.updatePerson() {
    put("/{id}") {
        val id = call.parameters["id"].toString()
        val personRequest = call.receive<PersonDto>()
        val person = personRequest.toPerson()

        val update = PersonService.updateById(id, person)
        if (update) {
            call.respond(HttpStatusCode.NoContent)
        }
        call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
    }
}

fun Route.getPersonById() {
    get("/{id}") {
        val id = call.parameters["id"].toString()

        PersonService.findById(id)?.let { person -> call.respond(person.toDto()) }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }
}

fun Route.getPersonBySearchTerm() {
    get("/search") {
        val name = call.request.queryParameters["name"]
        val age = call.request.queryParameters["age"]

        if (Objects.isNull(name)) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        val result = if (Objects.isNull(age)) {
            PersonService.findByNameAndAge(name = name!!, age = age!!.toInt()).map(Person::toDto)
        } else {
            PersonService.findByNameAndAgeExactly(name = name!!, age = age!!.toInt()).map(Person::toDto)
        }

        call.respond(result)
    }
}

fun Route.savePerson() {
    post {
        val request = call.receive<PersonDto>()
        val person = request.toPerson()

        PersonService.create(person)?.let { personId ->
            call.response.headers.append("User-Id-Header", personId.toString())
            call.respond(HttpStatusCode.Created)
        } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
    }
}