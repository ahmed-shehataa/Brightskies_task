package com.ashehata.brightskies_task.modules.recipes.di

import com.ashehata.brightskies_task.database.room.AppDatabase
import com.ashehata.brightskies_task.modules.recipes.data.local.dao.RecipesDao
import com.ashehata.brightskies_task.modules.recipes.data.local.source.RecipesLocalDataSource
import com.ashehata.brightskies_task.modules.recipes.data.local.source.RecipesLocalDataSourceImpl
import com.ashehata.brightskies_task.modules.recipes.data.remote.RecipesRemoteDataSource
import com.ashehata.brightskies_task.modules.recipes.data.remote.RecipesRemoteDataSourceImpl
import com.ashehata.brightskies_task.modules.recipes.data.repository.RecipesRepositoryImpl
import com.ashehata.brightskies_task.modules.recipes.data.retrofit.service.RecipesService
import com.ashehata.brightskies_task.modules.recipes.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class RecipesModule {

    @ViewModelScoped
    @Binds
    abstract fun bindRecipesRemoteDataSource(RecipesRemoteDataSourceImpl: RecipesRemoteDataSourceImpl): RecipesRemoteDataSource


    @ViewModelScoped
    @Binds
    abstract fun bindRecipesRepository(RecipesRepoImpl: RecipesRepositoryImpl): RecipesRepository


    @ViewModelScoped
    @Binds
    abstract fun bindRecipesLocalDataSource(RecipesLocalDataSourceImpl: RecipesLocalDataSourceImpl): RecipesLocalDataSource

    companion object {
        @ViewModelScoped
        @Provides
        fun provideRecipesService(retrofit: Retrofit): RecipesService =
            retrofit.create(RecipesService::class.java)

        @Provides
        @ViewModelScoped
        fun provideExhibitorsDao(appDatabase: AppDatabase): RecipesDao =
            appDatabase.recipesDao()
    }
}