package com.ebdz.domain.repository

import com.ebdz.domain.model.TemplateItem

/**
 * Repository contract for the `features:template` skeleton feature. A real feature built
 * from this template would replace [getItems]'s implementation with one composing
 * `data:local` (cache) and `data:remote` (network).
 */
interface TemplateRepository {
    suspend fun getItems(): Result<List<TemplateItem>>
}
