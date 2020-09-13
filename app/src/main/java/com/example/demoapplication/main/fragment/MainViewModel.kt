package com.example.demoapplication.main.fragment

import com.example.demoapplication.base.BaseViewModel
import com.example.domain.models.DataModel
import com.example.domain.usecase.GetDataUseCase
import com.example.domain.usecase.base.Error
import com.example.domain.usecase.base.Result
import kotlinx.coroutines.channels.ReceiveChannel

class MainViewModel(private var getDataUseCase: GetDataUseCase) : BaseViewModel<DataModel>() {

    override val receiveChannel: ReceiveChannel<Result<DataModel, Error>>
        get() = getDataUseCase.receiveChannel

    override fun handleLoading(state: Result.Loading) {
    }

    override fun handleSuccess(state: DataModel) {
    }

    override fun handleFailure(state: Error) {
    }

    fun getData() {
        getDataUseCase.invoke(0)
    }
}
