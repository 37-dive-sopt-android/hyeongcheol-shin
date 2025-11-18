package com.sopt.dive.network.service

import com.sopt.dive.network.model.response.BaseResponse
import com.sopt.dive.network.model.request.SignUpRequest
import com.sopt.dive.network.model.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("users")
    suspend fun createAccount(
        @Body request: SignUpRequest
    ): BaseResponse<SignUpResponseDto>
}