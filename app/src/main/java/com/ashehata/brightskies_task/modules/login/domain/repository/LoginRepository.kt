package com.ashehata.brightskies_task.modules.login.domain.repository

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel


interface LoginRepository {

    suspend fun login(email: String, password: String): UserDomainModel

}