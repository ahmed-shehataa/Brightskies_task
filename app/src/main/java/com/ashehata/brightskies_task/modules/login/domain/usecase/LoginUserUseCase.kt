package com.ashehata.brightskies_task.modules.login.domain.usecase

import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend fun execute(
        email: String,
        password: String,
    ): UserDomainModel {
        return repository.login(email, password)
    }
}