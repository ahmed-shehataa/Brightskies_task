package com.ashehata.brightskies_task.modules.login.data.repository


import com.ashehata.brightskies_task.modules.login.data.remote.LoginRemoteDataSource
import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import com.ashehata.brightskies_task.modules.user.data.mapper.toDomainModel
import com.ashehata.brightskies_task.modules.user.data.model.UserDataModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.IOException

class LoginRepositoryImplTest {

    lateinit var loginRemoteDataSource: LoginRemoteDataSource
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRemoteDataSource = mock()
        loginRepository = LoginRepositoryImpl(loginRemoteDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when login() with correct credentials returns valid DomainUserModel`() = runBlocking {
        // arrange
        val email = "test@gmail.com"
        val password = "123456"
        val expectedUser = UserDataModel(
            name = "Test",
            email = email
        )

        // act
        `when`(loginRemoteDataSource.login(email, password)).thenReturn(expectedUser)
        val actualUser = loginRepository.login(
            email = email,
            password = password
        )

        // assert
        Assert.assertEquals(expectedUser.toDomainModel(), actualUser)
    }

    @Test(expected = IOException::class)
    fun `when login() with wrong credentials throws IOException`() = runBlocking {
        // arrange
        val email = "mo@gmail.com"
        val password = "123456"


        // act
        `when`(loginRemoteDataSource.login(email, password)).then {
            throw IOException()
        }

        val res = loginRepository.login(
            email = email,
            password = password
        )

    }


}