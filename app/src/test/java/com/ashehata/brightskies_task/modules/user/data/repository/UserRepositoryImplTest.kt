package com.ashehata.brightskies_task.modules.user.data.repository

import com.ashehata.brightskies_task.modules.user.data.local.UserLocalDataSource
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times

class UserRepositoryImplTest {

    private var userLocalDataSource: UserLocalDataSource = Mockito.mock()
    private var userRepository = UserRepositoryImpl(userLocalDataSource)


    @Test
    fun getUser() = runBlocking {
        val user = UserDomainModel(
            name = "test",
            email = "test@co.com"
        )

        Mockito.`when`(userLocalDataSource.getUser()).thenReturn(user)

        val actual = userRepository.getUser()

        assertEquals(user, actual)
    }

    @Test
    fun setUser() = runBlocking {
        val user = UserDomainModel(
            name = "test",
            email = "test@co.com"
        )

        userRepository.setUser(user)

        Mockito.verify(userLocalDataSource, times(1)).setUser(user)
    }

    @Test
    fun clearUser() = runBlocking {
        userRepository.logoutUser()
        Mockito.verify(userLocalDataSource, times(1)).clearUser()
    }

    @Test
    fun `checkIfUserLoggedIn returns true`() = runBlocking {
        Mockito.`when`(userLocalDataSource.checkIfUserLoggedIn()).thenReturn(true)

        val actual = userRepository.checkIfUserLoggedIn()

        assertEquals(true, actual)
    }

    @Test
    fun `checkIfUserLoggedIn returns false`() = runBlocking {
        Mockito.`when`(userLocalDataSource.checkIfUserLoggedIn()).thenReturn(false)

        val actual = userRepository.checkIfUserLoggedIn()

        assertEquals(false, actual)
    }


}