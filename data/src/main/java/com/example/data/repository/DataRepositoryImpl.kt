package com.example.data.repository

import com.example.data.response.DataResponse
import com.example.data.service.DataService
import com.example.domain.models.DataModel
import com.example.domain.repository.DataRepository
import com.example.domain.usecase.base.ErrorModel
import com.example.domain.usecase.base.Result

class DataRepositoryImpl constructor(private val dataService: DataService) : DataRepository {

    override suspend fun getData(page: Int): Result<List<DataModel>, ErrorModel> {
        return try {
            val response = dataService.getData()

            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(map(body))
            } else {
                Result.Error(ErrorModel.ResponseError)
            }
        } catch (error: Exception) {
            Result.Error(ErrorModel.NetworkError)
        }
    }

    private fun map(body: List<DataResponse>): List<DataModel> = body.map {
        DataModel(it.name, it.url, it.gender, it.culture)
    }

}