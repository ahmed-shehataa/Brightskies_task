package com.ashehata.brightskies_task.modules.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ashehata.brightskies_task.common.presentation.GeneralObservers
import com.ashehata.brightskies_task.modules.NavGraphs
import com.ashehata.brightskies_task.modules.destinations.LoginScreenDestination
import com.ashehata.brightskies_task.modules.destinations.RecipesScreenDestination
import com.ashehata.brightskies_task.modules.destinations.SplashScreenDestination
import com.ashehata.brightskies_task.modules.recipes.presentation.viewmodel.RecipesViewModel
import com.ashehata.brightskies_task.ui.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AppTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    dependenciesContainerBuilder = {
                        dependency(hiltViewModel<RecipesViewModel>(this@HomeActivity))
                    }
                )
            }
            GeneralObservers<HomeState, HomeViewModel>(viewModel = viewModel) {

                val navOptions = navOptions {
                    popUpTo(SplashScreenDestination.route) {
                        inclusive = true
                    }
                }

                when (it) {
                    HomeState.OpenLoginScreen -> {
                        navController.navigate(
                            LoginScreenDestination.route,
                            navOptions = navOptions
                        )
                    }

                    HomeState.OpenRecipesScreen -> {
                        navController.navigate(
                            RecipesScreenDestination.route,
                            navOptions = navOptions
                        )
                    }
                }
            }
        }

    }
}
