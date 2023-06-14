package com.ashehata.brightskies_task.modules.user.data.local

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel


interface UserLocalDataSource {
    suspend fun getUser(): UserDomainModel
    suspend fun setUser(user: UserDomainModel)
    suspend fun clearUser()
    fun checkIfUserLoggedIn(): Boolean
}