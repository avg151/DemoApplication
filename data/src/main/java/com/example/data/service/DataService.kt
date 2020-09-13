package com.example.data.service

import com.example.data.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface DataService {

    @GET("api/characters/2")
    suspend fun getData(): Response<DataResponse>
}