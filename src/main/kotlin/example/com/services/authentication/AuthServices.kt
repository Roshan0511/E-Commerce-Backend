package example.com.services.authentication

import example.com.request.authentication.TempVendorRegistrationRequest
import example.com.response.authentication.TempVendorRegistrationResponse

interface AuthServices {
    suspend fun insertTempVendor(params: TempVendorRegistrationRequest) : TempVendorRegistrationResponse?
}