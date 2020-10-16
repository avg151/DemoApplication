package com.example.domain.usecase.base

sealed class Result<out S, out E> {
    class Success<out T>(val successData: T) : Result<T, Nothing>()
    class Error<out E>(val errorData: E) : Result<Nothing, E>()
    object Loading : Result<Nothing, Nothing>()

    fun handleResult(
        loadingBlock: (Loading) -> Unit = {},
        successBlock: (S) -> Unit = {},
        errorBlock: (E) -> Unit = {}
    ) {
        when (this) {
            is Loading -> loadingBlock(this)
            is Success -> successBlock(successData)
            is Error -> errorBlock(errorData)
        }
    }
}