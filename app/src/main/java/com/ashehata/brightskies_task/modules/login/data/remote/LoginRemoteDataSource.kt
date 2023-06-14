package com.ashehata.brightskies_task.modules.login.data.remote

import com.ashehata.brightskies_task.modules.user.data.model.UserDataModel


interface LoginRemoteDataSource {

    suspend fun login(
        email: String,
        password: String,
    ): UserDataModel

}