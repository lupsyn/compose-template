package com.ebdz.features.preference

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ebdz.libraries.designsystem.Theme
import com.ebdz.libraries.designsystem.blue700
import com.ebdz.libraries.designsystem.components.Toolbar
import com.ebdz.libraries.designsystem.lightGreen700
import java.util.Locale

/**
 * About screen.
 */
@Composable
fun About(onUpPress: () -> Unit) {
    Scaffold(
        topBar = { Toolbar(onUpPress = onUpPress) },
        content = { AboutContent(modifier = Modifier.padding(it)) }
    )
}

@Composable
private fun AboutContent(modifier: Modifier) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ContentHeader()
        Text(
            text = stringResource(id = R.string.about_description),
            style = MaterialTheme.typography.body1,
            lineHeight = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
        ContentCallToAction()
    }
}

@Composable
private fun ContentHeader() {
    val infiniteTransition = rememberInfiniteTransition()

    val color by infiniteTransition.animateColor(
        initialValue = blue700,
        targetValue = lightGreen700,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10_000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = color)
    ) {
        val appName = "app_name".lowercase(Locale.getDefault())
        Text(
            text = appName,
            style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.surface)
        )
    }
}

@Composable
private fun ContentCallToAction() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        val context = LocalContext.current
        Button(onClick = { }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.about_cd_github)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = stringResource(id = R.string.about_button_project))
        }
    }
}

private const val PROJECT_URL = "https://github.com/lupsyn/compose-template"

@Preview
@Composable
fun AboutPreview() {
    Theme {
        About(onUpPress = { })
    }
}
