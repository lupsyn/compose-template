package com.ebdz.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ebdz.compose.navigation.NavGraph
import com.ebdz.designsystem.Theme

/**
 * Main Alkaa Activity.
 */
internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Theme {
                NavGraph()
            }
        }
    }
}
