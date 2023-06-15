package com.ashehata.brightskies_task.modules.login.data.repository

import com.ashehata.brightskies_task.modules.login.data.remote.LoginRemoteDataSource
import com.ashehata.brightskies_task.modules.user.data.model.UserDataModel
import java.io.IOException

class FakeLoginRemoteDataSource : LoginRemoteDataSource {

    override suspend fun login(email: String, password: String): UserDataModel {
        if (email == "test@gmail.com" && password == "123456")
            return UserDataModel(
                name = "Test",
                email = "test@gmail.com"
            )
        else
            throw IOException()
    }
}