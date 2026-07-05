package com.ebdz.features.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ebdz.domain.model.TemplateItem
import com.ebdz.features.template.presentation.TemplateAction
import com.ebdz.features.template.presentation.TemplateEffect
import com.ebdz.features.template.presentation.TemplateViewModel
import com.ebdz.libraries.designsystem.components.DefaultFullscreenContent
import com.ebdz.libraries.designsystem.components.LoadingContent
import com.ebdz.libraries.designsystem.components.TitleWithString
import org.koin.compose.viewmodel.koinViewModel

/**
 * Skeleton screen wired to [TemplateViewModel]. Copy this shape when starting a real feature.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateScreen(
    viewModel: TemplateViewModel = koinViewModel(),
    onShowMessage: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAction(TemplateAction.LoadItems)
    }

    LaunchedEffect(Unit) {
        viewModel.effectsFlow.collect { effect ->
            when (effect) {
                is TemplateEffect.ShowMessage -> onShowMessage(effect.message)
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Template") }) }
    ) { paddingValues ->
        when {
            state.isLoading && state.items.isEmpty() -> LoadingContent()

            state.error != null && state.items.isEmpty() -> DefaultFullscreenContent(
                imageIconWithContentDescriptor = {},
                title = { TitleWithString(state.error.orEmpty()) },
                modifier = Modifier.padding(paddingValues)
            )

            else -> LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                items(state.items) { item: TemplateItem ->
                    ListItem(headlineContent = { Text(item.title) })
                }
            }
        }
    }
}
