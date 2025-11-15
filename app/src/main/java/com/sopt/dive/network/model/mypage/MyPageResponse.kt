package com.sopt.dive.network.model.mypage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponseDto(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: MyPageResponseDataDto,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)

@Serializable
data class MyPageResponseDataDto(
    @SerialName("age")
    val age: Int,
    @SerialName("email")
    val email: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("username")
    val username: String
)