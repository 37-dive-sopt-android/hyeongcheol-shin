package com.sopt.dive.network.service

import com.sopt.dive.network.model.mypage.MyPageResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageService {
    @GET("users/{id}")
    suspend fun getMyProfile(
        @Path("id") id: Long
    ): MyPageResponseDto
}