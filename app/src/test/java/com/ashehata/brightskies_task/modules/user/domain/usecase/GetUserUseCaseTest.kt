package com.ashehata.brightskies_task.modules.user.domain.usecase

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import com.ashehata.brightskies_task.modules.user.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException

class GetUserUseCaseTest {

    private var userRepository: UserRepository = Mockito.mock()
    private var getUserUseCase = GetUserUseCase(userRepository)


    @Test
    fun `execute() GetUserUseCase when user is logged in returns UserDomainModel`() = runBlocking {
        val user = UserDomainModel(
            name = "test",
            email = "test@co.com"
        )
        Mockito.`when`(userRepository.getUser()).thenReturn(user)

        val actual = getUserUseCase.execute()

        assertEquals(user, actual)
    }

    @Test(expected = IOException::class)
    fun `execute() GetUserUseCase when user is not logged in throws IOException`() = runBlocking {
        Mockito.`when`(userRepository.getUser()).then {
            throw IOException()
        }

        val actual = getUserUseCase.execute()
    }


}