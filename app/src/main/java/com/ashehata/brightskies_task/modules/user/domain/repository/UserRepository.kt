package com.ashehata.brightskies_task.modules.user.domain.repository

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel


interface UserRepository {
    suspend fun getUser(): UserDomainModel
    suspend fun setUser(user: UserDomainModel)
    suspend fun logoutUser()
    fun checkIfUserLoggedIn(): Boolean
}
