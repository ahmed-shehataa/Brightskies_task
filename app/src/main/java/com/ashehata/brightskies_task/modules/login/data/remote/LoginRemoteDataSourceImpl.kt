package com.ashehata.brightskies_task.modules.login.data.remote


import com.ashehata.brightskies_task.modules.user.data.model.UserDataModel
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


class LoginRemoteDataSourceImpl @Inject constructor(): LoginRemoteDataSource {

    override suspend fun login(
        email: String,
        password: String,
    ): UserDataModel {

        delay(2.53.seconds)
        if (email == "ahmed@gmail.com" && password == "123456")
            return UserDataModel(
                name = "Ahmed Shehata",
                email = "ahmed@gmail.com"
            )
        else
            throw IOException()
    }

}
