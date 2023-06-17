package com.ashehata.brightskies_task.modules.home

import com.ashehata.brightskies_task.base.BaseEvent
import com.ashehata.brightskies_task.base.BaseViewModel
import com.ashehata.brightskies_task.modules.user.domain.usecase.CheckUserIsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkUserIsLoggedInUseCase: CheckUserIsLoggedInUseCase,
) : BaseViewModel<BaseEvent, HomeViewState, HomeState>() {


    init {
        launchCoroutine {
            val isLoggedIn = checkUserIsLoggedInUseCase.execute()
            setState {
                if (isLoggedIn) {
                    HomeState.OpenRecipesScreen
                } else HomeState.OpenLoginScreen
            }
        }
    }

    override fun handleEvents(event: BaseEvent) {

    }

    override fun createInitialViewState(): HomeViewState {
        return HomeViewState()
    }

}
