package com.ebdz.data.remote.network

import com.ebdz.domain.model.TemplateItem
import kotlinx.serialization.Serializable

/**
 * Illustrative DTO/mapper pair showing how a real feature would decode a network response and
 * translate it into a domain model, without leaking the DTO past this module.
 */
@Serializable
data class TemplateItemDto(
    val id: String,
    val title: String
)

fun TemplateItemDto.toDomain(): TemplateItem = TemplateItem(id = id, title = title)
