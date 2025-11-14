package com.sopt.dive.network.model.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: SignUpDataResponseDto
)

@Serializable
data class SignUpDataResponseDto(
    @SerialName("id")
    val id: Long,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("status")
    val status: String
)
