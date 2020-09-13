package com.example.data.repository

import com.example.data.service.DataService
import com.example.domain.models.DataModel
import com.example.domain.repository.DataRepository
import com.example.domain.usecase.base.Error
import com.example.domain.usecase.base.Result

class DataRepositoryImpl constructor(private val dataService: DataService) : DataRepository {

    override suspend fun getData(page: Int): Result<DataModel, Error> {
        try {
            val response = dataService.getData()

            return if (response.isSuccessful && response.body() != null) {
                val successData = DataModel(response.body()!!.name)
                Result.Success(successData)
                // return Result.Success(response.body()!!.map {
                //     return@map DataModel(
                //         it.id,
                //         url = it.imageUrls.full,
                //         color = it.color,
                //         width = it.width,
                //         height = it.height
                //     )
                // })
            } else {
                Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            return Result.Failure(Error.NetworkError)
        }
    }

}