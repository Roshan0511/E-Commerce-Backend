package example.com.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Vendors : Table("vendors") {
    private val vendorId = varchar("vendor_id", 32)
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val email = varchar("email", 50)
    val phoneNumber = varchar("phone_number", 15)
    val password = varchar("password", 20)
    val confirmPassword = varchar("confirm_password", 20)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(vendorId)
}