package com.ashehata.brightskies_task.modules.login.di


import com.ashehata.brightskies_task.modules.login.data.remote.LoginRemoteDataSource
import com.ashehata.brightskies_task.modules.login.data.remote.LoginRemoteDataSourceImpl
import com.ashehata.brightskies_task.modules.login.data.repository.LoginRepositoryImpl
import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {

    @ViewModelScoped
    @Binds
    abstract fun bindsLoginRepo(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @ViewModelScoped
    @Binds
    abstract fun bindsLoginRemoteDataSource(loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl): LoginRemoteDataSource

}
