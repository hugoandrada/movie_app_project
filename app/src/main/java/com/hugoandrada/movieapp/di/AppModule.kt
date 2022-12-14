package com.hugoandrada.movieapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.hugoandrada.movieapp.data.local.AppDatabase
import com.hugoandrada.movieapp.repo.api.ApiService
import com.hugoandrada.movieapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): ApiService =
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "favoritos"
        ).build()
}