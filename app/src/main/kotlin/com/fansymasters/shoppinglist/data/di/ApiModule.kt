package com.fansymasters.shoppinglist.data.di

import com.fansymasters.shoppinglist.BuildConfig
import com.fansymasters.shoppinglist.data.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {

    @Provides
    @Authenticated
    fun providesAuthRetrofitApiBuilder(
        @Authenticated client: OkHttpClient
    ): Retrofit = Retrofit.Builder()

        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @NotAuthenticated
    fun providesNoAuthRetrofitApiBuilder(
        @NotAuthenticated client: OkHttpClient
    ): Retrofit = Retrofit.Builder()

        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @NotAuthenticated
    fun providesNotAuthHttpClient() = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }
        .build()

    @Provides
    @Authenticated
    fun providesAuthHttpClient() = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }
        .addInterceptor(interceptor())
        .build()

    private fun interceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(BearerToken.TOKEN_KET, "Bearer ${BearerToken.token}")
                .build()
        )
    }
}

object BearerToken {
    const val TOKEN_KET = "Authorization"
    var token: String = ""
}