package com.kamol.service.route

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

private val userData= "{\"users\": [\"Nate\", \"Megan\", \"John\"]}"
fun Routing.root(){
    get("/") {
        call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
    }
}
fun Routing.rootPost(){
    post("/") {
        val post= call.receive<String>()
        call.respondText("Received $post from the post body", contentType = ContentType.Text.Plain)
    }
}
fun Routing.rootUser(){
    get("/users"){
        call.respond(HttpStatusCode.OK, userData)
    }
}