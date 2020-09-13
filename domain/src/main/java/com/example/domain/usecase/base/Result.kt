package com.example.domain.usecase.base

sealed class Result<out T, out R> {
    class Success<out T>(val successData: T) : Result<T, Nothing>()
    class Failure<out R : Error>(val errorData: R) : Result<Nothing, R>()
    object Loading : Result<Nothing, Nothing>()

    fun handleResult(
        loadingBlock: (Loading) -> Unit = {},
        successBlock: (T) -> Unit = {},
        failureBlock: (R) -> Unit = {}
    ) {
        when (this) {
            is Loading -> loadingBlock(this)
            is Success -> successBlock(successData)
            is Failure -> failureBlock(errorData)
        }
    }
}