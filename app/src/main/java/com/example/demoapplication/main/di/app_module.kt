package com.example.demoapplication.main.di

import com.example.data.repository.DataRepositoryImpl
import com.example.data.service.DataService
import com.example.demoapplication.main.view.fragment.MainViewModel
import com.example.domain.repository.DataRepository
import com.example.domain.usecase.GetDataUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModules = module {
    single(named("remote")) { DataRepositoryImpl(get(named(API))) as DataRepository }
}
val useCaseModules = module {
    factory(named(GET_DATA_USECASE)) {
        GetDataUseCase(get(named("remote")))
    }
}
val networkModules = module {
    single(named(OKHTTP_INSTANCE)) { createOkHttpClient() }
    single(named(API)) { createWebService<DataService>(get(named(OKHTTP_INSTANCE)), BASE_URL) }
}

val viewModels = module {
    viewModel {
        MainViewModel(get(named(GET_DATA_USECASE)))
    }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}


private const val BASE_URL = "https://www.anapioficeandfire.com/"
private const val OKHTTP_INSTANCE = "OkHttp"
private const val API = "Api"
private const val GET_DATA_USECASE = "getDataUseCase"