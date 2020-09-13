package com.example.domain.usecase.base

sealed class Error {

    object NetworkError : Error()
    object ResponseError : Error()
}