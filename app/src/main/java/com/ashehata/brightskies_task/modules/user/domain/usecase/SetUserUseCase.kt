package com.ashehata.brightskies_task.modules.user.domain.usecase

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import com.ashehata.brightskies_task.modules.user.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(user: UserDomainModel) {
        userRepository.setUser(user)
    }
}