package com.example.domain.usecase

import com.example.domain.models.DataModel
import com.example.domain.repository.DataRepository
import com.example.domain.usecase.base.BaseUseCase
import com.example.domain.usecase.base.Result

class GetDataUseCase(private val dataRepository: DataRepository) : BaseUseCase<Int, DataModel>() {

    override suspend fun run(params: Int) {
        resultChannel.send(Result.Loading)
        resultChannel.send(dataRepository.getData(params))
    }

}