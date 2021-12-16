package com.ebdz.compose.presentation.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebdz.compose.model.HomeSection
import com.ebdz.designsystem.Theme
import com.ebdz.preference.presentation.PreferenceSection

/**
 * App Home screen.
 */
@Composable
fun HomeScreen(onAboutClick: () -> Unit) {
    val (currentSection, setCurrentSection) = rememberSaveable { mutableStateOf(HomeSection.Home) }
    val navItems = HomeSection.values().toList()
    val homeModifier = Modifier.padding(bottom = 56.dp)

    val actions = HomeActions(
        onAboutClick = onAboutClick,
        setCurrentSection = setCurrentSection,
    )

    Crossfade(currentSection) { homeSection ->
        HomeScaffold(
            homeSection = homeSection,
            modifier = homeModifier,
            navItems = navItems,
            actions = actions
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScaffold(
    homeSection: HomeSection,
    modifier: Modifier,
    navItems: List<HomeSection>,
    actions: HomeActions
) {
    Scaffold(
        topBar = {
            TopBar(currentSection = homeSection)
        },
        content = {
            Content(
                homeSection = homeSection,
                modifier = modifier,
                actions = actions
            )
        },
        bottomBar = {
            BottomNav(
                currentSection = homeSection,
                onSectionSelect = actions.setCurrentSection,
                items = navItems
            )
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    homeSection: HomeSection,
    modifier: Modifier,
    actions: HomeActions
) {
    when (homeSection) {
        HomeSection.Settings -> {
            PreferenceSection(
                modifier = modifier,
                onAboutClick = actions.onAboutClick
            )
        }
        else -> {}
    }
}

@Composable
private fun TopBar(currentSection: HomeSection) {
    TopAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.h5,
                text = stringResource(currentSection.title)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomNav(
    currentSection: HomeSection,
    onSectionSelect: (HomeSection) -> Unit,
    items: List<HomeSection>
) {
    BottomAppBar(backgroundColor = MaterialTheme.colors.background) {
        items.forEach { section ->
            val selected = section == currentSection
            val colorState = animateColorAsState(
                if (selected) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onSecondary
                }
            )
            BottomIcon(
                section = section,
                tint = colorState.value,
                onSectionSelect = onSectionSelect,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomIcon(
    section: HomeSection,
    tint: Color,
    onSectionSelect: (HomeSection) -> Unit,
    modifier: Modifier
) {
    val title = stringResource(section.title)
    IconButton(
        onClick = { onSectionSelect(section) },
        modifier = modifier
    ) {
        Icon(imageVector = section.icon, tint = tint, contentDescription = title)
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    Theme {
        TopBar(HomeSection.Home)
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {
    Theme {
        BottomNav(
            currentSection = HomeSection.Home,
            onSectionSelect = {},
            items = HomeSection.values().toList()
        )
    }
}
