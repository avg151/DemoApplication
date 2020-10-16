package com.example.domain.repository

import com.example.domain.models.DataModel
import com.example.domain.usecase.base.ErrorModel
import com.example.domain.usecase.base.Result

interface DataRepository {

    suspend fun getData(page: Int): Result<List<DataModel>, ErrorModel>

}