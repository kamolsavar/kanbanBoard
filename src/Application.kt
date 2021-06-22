package com.kamol

import com.kamol.model.Board
import com.kamol.model.Staff
import com.kamol.model.Task
import com.kamol.service.route.root
import com.kamol.service.route.rootPost
import com.kamol.service.route.rootUser
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.jackson.jackson
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    // In memory / keep alive between connections/transactions
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
    transaction {
        SchemaUtils.create(Staff, Task, Board)
        Staff.insert {
            it[name] = "Kamol"
            it[team] = "Invincible"
            it[role] = "QE"
        }

        Staff.insert {
            it[name] = "Ben"
            it[team] = "Invincible"
            it[role] = "Dev"
        }

        Staff.insert {
            it[name] = "Joe"
            it[team] = "Royal"
            it[role] = "BA"
        }
    }
    routing {
        this.root()
        this.rootPost()
        this.rootUser()
    }


    // install different features
    install(ContentNegotiation) {
        gson {
        }
    }

    install(Locations) {
    }

//    install(StatusPages) {
//        exception<AuthenticationException> { cause ->
//            call.respond(HttpStatusCode.Unauthorized)
//        }
//        exception<AuthorizationException> { cause ->
//            call.respond(HttpStatusCode.Forbidden)
//        }
//    }

    install(AutoHeadResponse) {

    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

