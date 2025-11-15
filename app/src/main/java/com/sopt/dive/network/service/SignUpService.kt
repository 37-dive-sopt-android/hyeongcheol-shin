package com.sopt.dive.network.service

import com.sopt.dive.network.model.signup.SignUpRequest
import com.sopt.dive.network.model.signup.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("users")
    suspend fun createAccount(
        @Body request: SignUpRequest
    ): SignUpResponseDto
}