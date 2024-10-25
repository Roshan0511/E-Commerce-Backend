package example.com.repository.authentication

import example.com.request.authentication.TempVendorRegistrationRequest
import example.com.services.authentication.AuthServices
import example.com.utils.BaseResponse

class AuthRepositoryImpl(
    private val authServices: AuthServices
) : AuthRepository {

    override suspend fun insertTempVendor(params: TempVendorRegistrationRequest): BaseResponse<Any> {
        val temVendorData = authServices.insertTempVendor(params)
        return if (temVendorData != null) {
            BaseResponse.SuccessResponse(
                data = temVendorData,
                message = "Otp Sent Successfully",
                isSuccess = true
            )
        } else {
            BaseResponse.ErrorResponse(isSuccess = false, message = "Vendor already registered with this email or phone number.")
        }
    }
}