package com.ebdz.domain.di

import com.ebdz.domain.usecase.GetTemplateItemsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

/**
 * Domain dependency injection module.
 */
val domainModule = module {
    factoryOf(::GetTemplateItemsUseCase)
}
