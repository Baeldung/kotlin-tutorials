@file:JvmName("APIServer")


import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.serialization.gson.*
import io.ktor.server.request.path
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.event.Level

data class Author(val name: String, val website: String)
data class ToDo(var id: Int, val name: String, val description: String, val completed: Boolean)

fun main() {

    val toDoList = ArrayList<ToDo>();
    val jsonResponse = """{
            "id": 1,
            "task": "Pay waterbill",
            "description": "Pay water bill today",
        }"""
    println("JSon Response is: $jsonResponse")


    embeddedServer(Netty, 8080) {
        install(DefaultHeaders) {
            header("X-Developer", "Baeldung")
        }
        install(CallLogging) {
            level = Level.DEBUG
            filter { call -> call.request.path().startsWith("/todo") }
            filter { call -> call.request.path().startsWith("/author") }
        }
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        routing() {
            route("/todo") {
                post {
                    var toDo = call.receive<ToDo>();
                    toDo.id = toDoList.size;
                    toDoList.add(toDo);
                    call.respond("Added")

                }
                delete("/{id}") {
                    call.respond(toDoList.removeAt(call.parameters["id"]!!.toInt()));
                }
                get("/{id}") {

                    call.respond(toDoList[call.parameters["id"]!!.toInt()]);
                }
                get {
                    call.respond(toDoList);
                }
            }
        get("/author"){
            call.respond(Author("Baeldung","baeldung.com"));

        }


        }
    }.start(wait = true)
}