package com.ebdz.compose.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ebdz.features.template.TemplateScreen
import com.ebdz.libraries.designsystem.Theme

/**
 * Main Activity. Hosts the `features:template` skeleton screen - swap this for a real
 * navigation graph once this template has more than one feature.
 */
internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            Theme {
                TemplateScreen(
                    onShowMessage = { message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
                )
            }
        }
    }
}
