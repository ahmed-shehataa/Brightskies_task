package com.ashehata.brightskies_task.modules.login.domain.usecase

import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import java.io.IOException

class FakeLoginRepository : LoginRepository {

    override suspend fun login(email: String, password: String): UserDomainModel {
        if (email == "test@gmail.com" && password == "123456")
            return UserDomainModel(
                name = "Test",
                email = "test@gmail.com"
            )
        else
            throw IOException()
    }
}