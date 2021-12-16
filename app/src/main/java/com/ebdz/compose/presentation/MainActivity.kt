package com.ebdz.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ebdz.compose.navigation.NavGraph
import com.ebdz.designsystem.Theme

/**
 * Main Activity.
 */
internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            Theme {
                NavGraph()
            }
        }
    }
}
