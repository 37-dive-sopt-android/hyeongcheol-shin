package com.sopt.dive.network.service

import com.sopt.dive.network.model.signin.SignInRequest
import com.sopt.dive.network.model.signin.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("auth/login")
    suspend fun signIn(
        @Body request: SignInRequest
    ): SignInResponseDto
}