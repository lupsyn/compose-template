package com.ebdz.features.preference

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebdz.libraries.designsystem.Theme
import com.ebdz.libraries.extensions.getVersionName
import java.util.Locale

/**
 * Preference Section.
 *
 * @param modifier the decorator
 * @param onAboutClick function to be called when the about item is clicked
 */
@Composable
fun PreferenceSection(
    modifier: Modifier = Modifier,
    onAboutClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        PreferenceTitle(title = "Features")
        Separator()
        PreferenceTitle(title = "Settings")
        AboutItem(onAboutClick)
        VersionItem()
    }
}

@Composable
private fun PreferenceTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 16.dp)
            .height(32.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun TrackerItem(onTrackerClick: () -> Unit) {
    PreferenceItem(
        title = stringResource(id = R.string.preference_title_tracker),
        onItemClick = onTrackerClick
    )
}

@Composable
private fun VersionItem() {
    val title = stringResource(id = R.string.preference_title_version)
    val version = LocalContext.current.getVersionName()

    PreferenceItem(title = title, version)
}

@Composable
private fun AboutItem(onAboutClick: () -> Unit) {
    PreferenceItem(
        title = stringResource(id = R.string.preference_title_about),
        onItemClick = onAboutClick
    )
}

@Composable
private fun PreferenceItem(
    title: String,
    description: String? = null,
    onItemClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(start = 32.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            .fillMaxWidth()
            .height(48.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1
        )
        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
private fun Separator() {
    Spacer(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSecondary.copy(alpha = 0.7F))
    )
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun PreferencePreview() {
    Theme {
        PreferenceSection(onAboutClick = {})
    }
}
