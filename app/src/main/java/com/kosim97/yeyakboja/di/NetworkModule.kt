package com.kosim97.yeyakboja.di

import android.content.Context
import com.kosim97.data.api.CampingApi
import com.kosim97.data.api.GymApi
import com.kosim97.yeyakboja.BuildConfig
import com.kosim97.data.remote.gym.GymRemoteDataSource
import com.kosim97.domain.repository.GymRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }
    }

    @CampingRetrofit
    @Singleton
    @Provides
    fun provideCampingRetrofit(
        okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://openAPI.seoul.go.kr:8088/${BuildConfig.CAMPING_API_KEY}/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @GymRetrofit
    @Singleton
    @Provides
    fun provideGymRetrofit(
        okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://openAPI.seoul.go.kr:8088/${BuildConfig.GYM_API_KEY}/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGymApi(
        @GymRetrofit
        retrofit: Retrofit
    ): GymApi {
        return retrofit.create(GymApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCampingApi(
        @CampingRetrofit
        retrofit: Retrofit
    ): CampingApi {
        return retrofit.create(CampingApi::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GymRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CampingRetrofit