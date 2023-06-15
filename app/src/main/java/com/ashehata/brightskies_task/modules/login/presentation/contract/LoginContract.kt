package com.ashehata.brightskies_task.modules.login.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ashehata.brightskies_task.base.BaseEvent
import com.ashehata.brightskies_task.base.BaseState
import com.ashehata.brightskies_task.base.BaseViewState
import com.ashehata.brightskies_task.common.presentation.InputWrapper
import com.ashehata.brightskies_task.common.presentation.ValidationType

sealed class LoginEvent : BaseEvent {
    object OnLoginClicked : LoginEvent()
}

sealed class LoginState : BaseState {
    object OpenRecipesScreen : LoginState()
}

data class LoginViewState(
    override val isRefreshing: MutableState<Boolean> = mutableStateOf(false),
    override val isNetworkError: MutableState<Boolean> = mutableStateOf(false),
    override val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val email: InputWrapper = InputWrapper(validationType = ValidationType.Email),
    val password: InputWrapper = InputWrapper(validationType = ValidationType.Password),
) : BaseViewState