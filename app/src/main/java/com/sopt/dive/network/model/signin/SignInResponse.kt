package com.sopt.dive.network.model.signin
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SignInResponseDto(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: SignInResponseDataDto,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)

@Serializable
data class SignInResponseDataDto(
    @SerialName("message")
    val message: String,
    @SerialName("userId")
    val userId: Long
)