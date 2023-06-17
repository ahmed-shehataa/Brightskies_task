package com.ashehata.brightskies_task.modules.user.domain.usecase

import com.ashehata.brightskies_task.modules.user.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CheckUserIsLoggedInUseCaseTest {

    private var userRepository: UserRepository = Mockito.mock()
    private var checkUserIsLoggedInUseCase = CheckUserIsLoggedInUseCase(userRepository)


    @Test
    fun `execute() CheckUserIsLoggedInUseCase returns true`() = runBlocking {
        Mockito.`when`(userRepository.checkIfUserLoggedIn()).thenReturn(true)

        val actual = checkUserIsLoggedInUseCase.execute()

        Assert.assertEquals(true, actual)
    }

    @Test
    fun `execute() CheckUserIsLoggedInUseCase returns false`() = runBlocking {
        Mockito.`when`(userRepository.checkIfUserLoggedIn()).thenReturn(false)

        val actual = checkUserIsLoggedInUseCase.execute()

        Assert.assertEquals(false, actual)
    }


}