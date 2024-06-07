package com.gilbertohdz.data.di

import com.gilbertohdz.data.remote.TvMazeApi
import com.gilbertohdz.data.repository.TvMazeRepositoryImpl
import com.gilbertohdz.domain.repository.TvMazeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory {
        return KotlinJsonAdapterFactory()
    }

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi {
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideTvMazeApi(
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): TvMazeApi {
        return Retrofit.Builder()
            .baseUrl(TvMazeApi.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTvMazeRepository(
        api: TvMazeApi
    ): TvMazeRepository {
        return TvMazeRepositoryImpl(
            api = api
        )
    }
}