package com.ebdz.domain.usecase

import com.ebdz.domain.model.TemplateItem
import com.ebdz.domain.repository.TemplateRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetTemplateItemsUseCaseTest {

    @Test
    fun `invoke delegates to the repository`() = runTest {
        val items = listOf(TemplateItem(id = "1", title = "First"))
        val repository = object : TemplateRepository {
            override suspend fun getItems(): Result<List<TemplateItem>> = Result.success(items)
        }
        val useCase = GetTemplateItemsUseCase(repository)

        assertEquals(Result.success(items), useCase())
    }
}
