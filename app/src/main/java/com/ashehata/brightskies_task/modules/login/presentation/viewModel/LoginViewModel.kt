package com.ashehata.brightskies_task.modules.login.presentation.viewModel

import com.ashehata.brightskies_task.base.BaseViewModel
import com.ashehata.brightskies_task.modules.login.domain.usecase.LoginUserUseCase
import com.ashehata.brightskies_task.modules.login.presentation.contract.LoginEvent
import com.ashehata.brightskies_task.modules.login.presentation.contract.LoginState
import com.ashehata.brightskies_task.modules.login.presentation.contract.LoginViewState
import com.ashehata.brightskies_task.modules.user.domain.usecase.SetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setUserUseCase: SetUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
) : BaseViewModel<LoginEvent, LoginViewState, LoginState>() {


    override fun handleEvents(event: LoginEvent) {
        when (event) {
            LoginEvent.OnLoginClicked -> {
                login()
            }
        }
    }


    private fun login() {
        setLoading()
        launchCoroutine {
            val user = loginUserUseCase.execute(
                viewStates?.email?.text?.value?.trim() ?: "",
                viewStates?.password?.text?.value ?: "",
            )
            setUserUseCase.execute(user)
            setDoneLoading()
            setState { LoginState.OpenRecipesScreen }
        }
    }

    override fun createInitialViewState(): LoginViewState {
        return LoginViewState()
    }

}
