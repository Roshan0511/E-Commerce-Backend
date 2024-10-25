package example.com.routing.authentication

import example.com.repository.authentication.AuthRepository
import example.com.request.authentication.TempVendorRegistrationRequest
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.response.respond

fun Application.authRouting(repository: AuthRepository) {
    routing {
        route("/accounts") {
            route("/register") {
                post("/vendor") {
                    val params = call.receive<TempVendorRegistrationRequest>()
                    println("Received params: $params")
                    
                    val result = repository.insertTempVendor(params)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}