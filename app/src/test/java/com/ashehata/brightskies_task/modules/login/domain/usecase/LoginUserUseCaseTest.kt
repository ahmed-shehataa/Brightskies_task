package com.ashehata.brightskies_task.modules.login.domain.usecase

import com.ashehata.brightskies_task.modules.login.domain.repository.LoginRepository
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.io.IOException

class LoginUserUseCaseTest {

    lateinit var loginRepository: LoginRepository
    private lateinit var loginUserUseCase: LoginUserUseCase

    @Before
    fun setUp() {
        loginRepository = mock()
        loginUserUseCase = LoginUserUseCase(loginRepository)
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
        Mockito.`when`(loginRepository.login(email, password)).thenReturn(expectedUser)
        val actualUser = loginRepository.login(
            email = email,
            password = password
        )

        // assert
        assertEquals(expectedUser, actualUser)
    }

    @Test(expected = IOException::class)
    fun `when login() with wrong credentials throws IOException`() = runBlocking {
        // arrange
        val email = "mo@gmail.com"
        val password = "123456"

        // act
        Mockito.`when`(loginRepository.login(email, password)).then {
            throw IOException()
        }
        val actualUser = loginRepository.login(
            email = email,
            password = password
        )
    }
}