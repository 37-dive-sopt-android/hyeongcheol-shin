package com.sopt.dive.network.model.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String
)