package com.example.demoapplication.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.demoapplication.main.view.adapter.MainAdapter
import com.example.demoapplication.main.view.state.MainScreenErrorState
import com.example.demoapplication.main.view.state.MainScreenSuccessState
import com.example.domain.usecase.base.Result
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainScreenSuccessState, MainScreenErrorState>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var adapter = MainAdapter()

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
        viewModel.screenState().observe(viewLifecycleOwner, Observer(::render))

        val recyclerView: RecyclerView = view.findViewById(R.id.main_fragment_adapter)
        recyclerView.adapter = adapter
    }

    override fun renderLoading() {
    }

    override fun renderSuccess(screenState: Result.Success<MainScreenSuccessState>) {
        adapter.setData(screenState.successData.dataList)
    }

    override fun renderError(screenState: Result.Error<MainScreenErrorState>) {
    }
}
