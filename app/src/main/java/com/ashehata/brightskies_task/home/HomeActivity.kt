package com.ashehata.brightskies_task.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ashehata.brightskies_task.modules.NavGraphs
import com.ashehata.brightskies_task.ui.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root
                )
            }
        }
    }
}
