package com.ebdz.features.template.presentation

import app.cash.turbine.test
import com.ebdz.domain.model.TemplateItem
import com.ebdz.domain.repository.TemplateRepository
import com.ebdz.domain.usecase.GetTemplateItemsUseCase
import com.ebdz.libraries.test.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private class FakeTemplateRepository(private val result: Result<List<TemplateItem>>) : TemplateRepository {
    override suspend fun getItems(): Result<List<TemplateItem>> = result
}

@OptIn(ExperimentalCoroutinesApi::class)
class TemplateViewModelTest {

    private val mainDispatcherRule = MainDispatcherRule()

    @BeforeTest
    fun setup() = mainDispatcherRule.setUp()

    @AfterTest
    fun tearDown() = mainDispatcherRule.tearDown()

    @Test
    fun `loading items without a network or database succeeds with canned data`() = runTest {
        val items = listOf(TemplateItem(id = "1", title = "First"))
        val viewModel = TemplateViewModel(
            getTemplateItemsUseCase = GetTemplateItemsUseCase(FakeTemplateRepository(Result.success(items))),
            reducer = TemplateReducer()
        )

        viewModel.onAction(TemplateAction.LoadItems)

        assertEquals(TemplateState(items = items), viewModel.uiState.value)
    }

    @Test
    fun `a failure surfaces an error state`() = runTest {
        val viewModel = TemplateViewModel(
            getTemplateItemsUseCase = GetTemplateItemsUseCase(
                FakeTemplateRepository(Result.failure(IllegalStateException("boom")))
            ),
            reducer = TemplateReducer()
        )

        viewModel.onAction(TemplateAction.LoadItems)

        assertEquals("boom", viewModel.uiState.value.error)
    }

    @Test
    fun `selecting an item emits a one-off effect`() = runTest {
        val viewModel = TemplateViewModel(
            getTemplateItemsUseCase = GetTemplateItemsUseCase(FakeTemplateRepository(Result.success(emptyList()))),
            reducer = TemplateReducer()
        )

        viewModel.effectsFlow.test {
            viewModel.onAction(TemplateAction.SelectItem("42"))

            assertEquals(TemplateEffect.ShowMessage("Selected item 42"), awaitItem())
        }
    }
}
