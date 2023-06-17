package com.ashehata.brightskies_task.modules.user.data.local

import com.ashehata.brightskies_task.database.datastore.AppDataStore
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.times

class UserLocalDataSourceImplTest {

    private var appDataStore: AppDataStore = mock()
    private var userLocalDataSourceImpl = UserLocalDataSourceImpl(appDataStore)


    @Test
    fun getUser() = runBlocking {
        val user = UserDomainModel(
            name = "test",
            email = "test@co.com"
        )

        Mockito.`when`(appDataStore.getUser()).thenReturn(user)

        val actual = userLocalDataSourceImpl.getUser()

        Assert.assertEquals(user, actual)
    }

    @Test
    fun setUser() = runBlocking {
        val user = UserDomainModel(
            name = "test",
            email = "test@co.com"
        )

        userLocalDataSourceImpl.setUser(user)

        Mockito.verify(appDataStore, times(1)).setUser(user)
    }

    @Test
    fun clearUser() = runBlocking {
        userLocalDataSourceImpl.clearUser()
        Mockito.verify(appDataStore, times(1)).clearUser()
    }

    @Test
    fun `checkIfUserLoggedIn returns true`() = runBlocking {
        Mockito.`when`(appDataStore.getIsLoggedIn()).thenReturn(true)

        val actual =  userLocalDataSourceImpl.checkIfUserLoggedIn()

        Assert.assertEquals(true, actual)
    }

    @Test
    fun `checkIfUserLoggedIn returns false`() = runBlocking {
        Mockito.`when`(appDataStore.getIsLoggedIn()).thenReturn(false)

        val actual =  userLocalDataSourceImpl.checkIfUserLoggedIn()

        Assert.assertEquals(false, actual)
    }



}