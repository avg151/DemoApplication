package com.example.demoapplication.main.view.fragment

import com.example.demoapplication.base.BaseViewModel
import com.example.demoapplication.main.view.state.MainScreenErrorState
import com.example.demoapplication.main.view.state.MainScreenSuccessState
import com.example.domain.models.DataModel
import com.example.domain.usecase.GetDataUseCase
import com.example.domain.usecase.base.ErrorModel
import com.example.domain.usecase.base.Result
import kotlinx.coroutines.channels.ReceiveChannel

class MainViewModel(private var getDataUseCase: GetDataUseCase) :
    BaseViewModel<List<DataModel>, ErrorModel, MainScreenSuccessState, MainScreenErrorState>() {

    override val receiveChannel: ReceiveChannel<Result<List<DataModel>, ErrorModel>>
        get() = getDataUseCase.receiveChannel

    fun getData() {
        getDataUseCase.invoke(0)
    }

    override fun successState(result: List<DataModel>) = MainScreenSuccessState(result)

    override fun errorState(result: ErrorModel) = MainScreenErrorState(result.toString()) //TODO:add message
}
