package example.com.repository.authentication

import example.com.request.authentication.TempVendorRegistrationRequest
import example.com.utils.BaseResponse

interface AuthRepository {
    suspend fun insertTempVendor(params: TempVendorRegistrationRequest) : BaseResponse<Any>
}