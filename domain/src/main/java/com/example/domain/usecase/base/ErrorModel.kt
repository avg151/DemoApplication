package com.example.domain.usecase.base

sealed class ErrorModel {

    object NetworkError : ErrorModel()
    object ResponseError : ErrorModel()
}