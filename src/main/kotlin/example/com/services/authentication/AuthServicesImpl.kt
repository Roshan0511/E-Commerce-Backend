package example.com.services.authentication

import example.com.database.DatabaseFactory.dbQuery
import example.com.database.tables.TempVendor
import example.com.request.authentication.TempVendorRegistrationRequest
import example.com.response.authentication.TempVendorRegistrationResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.selectAll
import kotlin.random.Random

class AuthServicesImpl : AuthServices {

    override suspend fun insertTempVendor(params: TempVendorRegistrationRequest): TempVendorRegistrationResponse? {
        val existingVendor = dbQuery {
            TempVendor.selectAll()
                .where { (TempVendor.email eq params.email!!) or (TempVendor.phoneNumber eq params.phoneNumber!!) }
                .singleOrNull()
        }

        if (existingVendor != null) {
            return null
        }

        var vendorId = ""
        val randomOtp = Random.nextInt(100000, 1000000).toString()

        dbQuery {
            vendorId = TempVendor.insert {
                it[firstName] = params.firstName.toString()
                it[lastName] = params.lastName.toString()
                it[email] = params.email.toString()
                it[phoneNumber] = params.phoneNumber.toString()
                it[password] = params.password.toString()
                it[confirmPassword] = params.confirmPassword.toString()
                it[otp] = randomOtp
            }[TempVendor.vendorId]
        }

        return TempVendorRegistrationResponse(vendorId = vendorId, otp = randomOtp)
    }
}