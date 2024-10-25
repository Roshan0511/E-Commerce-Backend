package example.com.request.authentication

data class TempVendorRegistrationRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null
)
