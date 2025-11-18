package com.sopt.dive.network.model.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class SignInResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("userId")
    val userId: Long
)