package dev.jacaceresf.kmongo

import dev.jacaceresf.kmongo.plugins.configureRouting
import dev.jacaceresf.kmongo.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
