package com.example.demoapplication.base

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.base.Error
import com.example.domain.usecase.base.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T> : ViewModel(), CoroutineScope {

    private val job = Job()
    protected abstract val receiveChannel: ReceiveChannel<Result<T, Error>>

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    init {
        processStream()
    }

    private fun processStream() {
        launch {
            receiveChannel.consumeEach {
                resolve(it)
            }
        }
    }

    private fun resolve(value: Result<T, Error>) {
        value.handleResult(::handleLoading, ::handleSuccess, ::handleFailure)
    }

    abstract fun handleLoading(state: Result.Loading)

    abstract fun handleSuccess(state: T)

    abstract fun handleFailure(state: Error)

    override fun onCleared() {
        receiveChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }
}