package com.ebdz.data.repository

import com.ebdz.data.local.dao.SimpleDao
import com.ebdz.data.local.entity.SimpleEntity
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

private class FakeSimpleDao : SimpleDao {
    private val items = mutableListOf<SimpleEntity>()
    private var nextId = 1L

    override suspend fun getAll(): List<SimpleEntity> = items.toList()

    override suspend fun insert(entity: SimpleEntity): Long {
        val stored = entity.copy(id = nextId++)
        items.add(stored)
        return stored.id
    }

    override suspend fun deleteAll() {
        items.clear()
    }
}

class TemplateRepositoryImplTest {

    @Test
    fun `seeds canned data on first read`() = runTest {
        val repository = TemplateRepositoryImpl(FakeSimpleDao())

        val result = repository.getItems()

        assertEquals(3, result.getOrThrow().size)
    }

    @Test
    fun `second read returns the same seeded data`() = runTest {
        val dao = FakeSimpleDao()
        val repository = TemplateRepositoryImpl(dao)

        val first = repository.getItems().getOrThrow()
        val second = repository.getItems().getOrThrow()

        assertEquals(first, second)
    }
}
