package com.satyajit.newscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.satyajit.newscompose.data.local.NewsDao
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.model.Source
import com.satyajit.newscompose.presentation.navgraph.NavGraph
import com.satyajit.newscompose.ui.theme.NewsComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashCondition
            }
        }

        setContent {
            NewsComposeTheme {

                //get the boolean if the system is in dark or light mode
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                //side effects are used to perform task which is out of this composable function or scope
                //this is will be executed when successful re-composition occur
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = mainViewModel.startDestination)
                }
            }
        }
    }
}
