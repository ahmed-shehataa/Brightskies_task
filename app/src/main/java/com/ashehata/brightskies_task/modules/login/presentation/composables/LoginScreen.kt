package com.ashehata.brightskies_task.modules.login.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import com.ashehata.brightskies_task.common.presentation.GeneralObservers
import com.ashehata.brightskies_task.modules.destinations.LoginScreenDestination
import com.ashehata.brightskies_task.modules.destinations.RecipesScreenDestination
import com.ashehata.brightskies_task.modules.login.presentation.contract.LoginEvent
import com.ashehata.brightskies_task.modules.login.presentation.contract.LoginState
import com.ashehata.brightskies_task.modules.login.presentation.contract.LoginViewState
import com.ashehata.brightskies_task.modules.login.presentation.viewModel.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigator: DestinationsNavigator? = null
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val viewStates = remember {
        viewModel.viewStates ?: LoginViewState()
    }


    val email = remember {
        viewStates.email
    }

    val password = remember {
        viewStates.password
    }

    val isLoading = remember {
        viewStates.isLoading
    }

    val isButtonEnabled by remember(email.text, password.text) {
        derivedStateOf {
            email.isValid.value && password.isValid.value
        }
    }

    val onLoginClicked = remember {
        {
            keyboardController?.hide()
            viewModel.setEvent(
                LoginEvent.OnLoginClicked
            )
        }
    }


    LoginScreenContent(
        isLoading = isLoading.value,
        email = email,
        password = password,
        isButtonEnabled = isButtonEnabled,
        onLoginClicked = onLoginClicked
    )

    GeneralObservers<LoginState, LoginViewModel>(viewModel = viewModel) {
        when (it) {
            LoginState.OpenRecipesScreen -> {
                navigator?.navigate(RecipesScreenDestination, navOptions = navOptions {
                    popUpTo(LoginScreenDestination.route) {
                        inclusive = true
                    }
                })
            }
        }

    }
}