package com.ashehata.brightskies_task.modules.login.data.remote


import com.ashehata.brightskies_task.modules.user.data.model.UserDataModel
import java.io.IOException


class LoginRemoteDataSourceImpl : LoginRemoteDataSource {

    override suspend fun login(
        email: String,
        password: String,
    ): UserDataModel {

        if (email == "ahmed@gmail.com" && password == "Ahmed123456")
            return UserDataModel(
                name = "Ahmed Shehata",
                email = "ahmed@gmail.com"
            )
        else
            throw IOException()
    }

}
