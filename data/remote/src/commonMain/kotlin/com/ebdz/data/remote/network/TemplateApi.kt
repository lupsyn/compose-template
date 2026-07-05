package com.ebdz.data.remote.network

import com.ebdz.domain.model.TemplateItem

/**
 * Illustrative remote API surface for the `features:template` skeleton feature. Not wired into
 * the running app - `features:template` uses canned data by default (see
 * `openspec/changes/kmp-template-transformation/design.md`) - but ready for a fork of this
 * template to swap in.
 */
interface TemplateApi {
    suspend fun fetchItems(): Result<List<TemplateItem>>
}

class TemplateApiImpl(private val apiClient: ApiClient) : TemplateApi {

    override suspend fun fetchItems(): Result<List<TemplateItem>> =
        apiClient.get<List<TemplateItemDto>>("/template-items")
            .map { dtos -> dtos.map { it.toDomain() } }
}
