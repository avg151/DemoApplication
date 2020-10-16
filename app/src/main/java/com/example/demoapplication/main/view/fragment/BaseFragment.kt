package com.example.demoapplication.main.view.fragment

import androidx.appcompat.app.AppCompatDialogFragment
import com.example.domain.usecase.base.Result

abstract class BaseFragment<ScreenSuccessState, ScreenErrorState> : AppCompatDialogFragment() {

    internal fun render(screenState: Result<ScreenSuccessState, ScreenErrorState>) {
        when (screenState) {
            is Result.Loading -> renderLoading()
            is Result.Success -> renderSuccess(screenState)
            is Result.Error -> renderError(screenState)
        }
    }

    abstract fun renderLoading()

    abstract fun renderSuccess(screenState: Result.Success<ScreenSuccessState>)

    abstract fun renderError(screenState: Result.Error<ScreenErrorState>)
}
