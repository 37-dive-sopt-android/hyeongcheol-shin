package com.sopt.dive.ui.main.home

sealed class NetworkError {
    object NetworkException : NetworkError()
    object ServerError : NetworkError()
    data class UnknownError(val message: String) : NetworkError()

    fun getErrorMessage(): String = when (this){
        is NetworkException -> "네트워크 관련 예외 입니다."
        is ServerError -> "서버 관련 오류 입니다."
        is UnknownError -> this.message
    }
}