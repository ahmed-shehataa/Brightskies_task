package com.ashehata.brightskies_task.modules.user.domain.usecase

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import com.ashehata.brightskies_task.modules.user.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times

class SetUserUseCaseTest {

    private var userRepository: UserRepository = Mockito.mock()
    private var setUserUseCase = SetUserUseCase(userRepository)


    @Test
    fun `execute() SetUserUseCase`() = runBlocking {
        val user = UserDomainModel(
            name = "test",
            email = "test@co.com"
        )

        setUserUseCase.execute(user)

        Mockito.verify(userRepository, times(1)).setUser(user)
    }


}