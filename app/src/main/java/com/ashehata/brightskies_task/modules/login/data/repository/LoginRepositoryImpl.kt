package com.ashehata.brightskies_task.modules.login.data.repository


import com.ashehata.brightskies_task.modules.login.data.remote.LoginRemoteDataSource
import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import com.ashehata.brightskies_task.modules.user.data.mapper.toDomainModel
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {


    override suspend fun login(
        email: String,
        password: String,
    ): UserDomainModel {
        return loginRemoteDataSource.login(email, password).toDomainModel()
    }

}
