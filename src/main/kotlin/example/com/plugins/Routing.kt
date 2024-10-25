package example.com.plugins

import example.com.repository.authentication.AuthRepositoryImpl
import example.com.routing.authentication.authRouting
import example.com.services.authentication.AuthServicesImpl
import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/test1") {
            val text = "<h1>Hello From Ktor</h1>"
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }
    }


    // Authentication Flow
    val authServices = AuthServicesImpl()
    val authRepository = AuthRepositoryImpl(authServices)
    authRouting(authRepository)
}
