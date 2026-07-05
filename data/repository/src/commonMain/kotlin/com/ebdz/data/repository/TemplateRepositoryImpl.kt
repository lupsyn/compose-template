package com.ebdz.data.repository

import com.ebdz.data.local.dao.SimpleDao
import com.ebdz.data.local.entity.SimpleEntity
import com.ebdz.domain.model.TemplateItem
import com.ebdz.domain.repository.TemplateRepository

/**
 * Default [TemplateRepository]: reads from the local cache, seeding it with canned data the
 * first time so the skeleton feature has something to show without a network call. A real
 * feature built from this template would seed via `data:remote` instead.
 */
class TemplateRepositoryImpl(private val simpleDao: SimpleDao) : TemplateRepository {

    override suspend fun getItems(): Result<List<TemplateItem>> = runCatching {
        val cached = simpleDao.getAll()
        val entities = cached.ifEmpty {
            SEED_LABELS.forEach { label -> simpleDao.insert(SimpleEntity(label = label)) }
            simpleDao.getAll()
        }
        entities.map { entity -> TemplateItem(id = entity.id.toString(), title = entity.label) }
    }

    private companion object {
        val SEED_LABELS = listOf("First item", "Second item", "Third item")
    }
}
