package com.ashehata.brightskies_task.modules.login.data.repository


import com.ashehata.brightskies_task.modules.login.data.remote.LoginRemoteDataSource
import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class LoginRepositoryImplTest {

    lateinit var loginRemoteDataSource: LoginRemoteDataSource
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRemoteDataSource = FakeLoginRemoteDataSource()
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
        val expectedUser = UserDomainModel(
            name = "Test",
            email = email
        )

        // act
        val actualUser = loginRepository.login(
            email = email,
            password = password
        )

        // assert
        Assert.assertEquals(expectedUser, actualUser)
    }

    @Test(expected = IOException::class)
    fun `when login() with wrong credentials throws IOException`() = runBlocking {
        // arrange
        val email = "mo@gmail.com"
        val password = "123456"

        // act
        val actualUser = loginRepository.login(
            email = email,
            password = password
        )
    }


}