package com.ebdz.domain.usecase

import com.ebdz.domain.model.TemplateItem
import com.ebdz.domain.repository.TemplateRepository

class GetTemplateItemsUseCase(private val repository: TemplateRepository) {

    suspend operator fun invoke(): Result<List<TemplateItem>> = repository.getItems()
}
