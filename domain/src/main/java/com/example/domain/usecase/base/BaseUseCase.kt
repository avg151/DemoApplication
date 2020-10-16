package com.example.domain.usecase.base

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

typealias SimpleResult<T> = Result<T, ErrorModel>

abstract class BaseUseCase<in Params, T> : CoroutineScope {

    // region Members
    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default
    protected val resultChannel = Channel<SimpleResult<T>>()

    val receiveChannel: ReceiveChannel<SimpleResult<T>> = resultChannel

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    // endregion

    // region Functions
    protected abstract suspend fun run(params: Params)

    /**
     * By overriding invoke, we allow use cases to be called as "invoking"
     */
    operator fun invoke(params: Params) {
        launch {
            withContext(backgroundDispatcher) {
                run(params)
            }
        }
    }

    protected fun <T> startAsync(block: suspend () -> T): Deferred<T> = async(parentJob) {
        block()
    }

    /**
     * Should be called when use-case owner is destroyed
     * This will ensure coroutine is cancelled if it's still running some tasks
     */
    fun clear() {
        resultChannel.close()
        parentJob.cancel()
    }
    // endregion
}