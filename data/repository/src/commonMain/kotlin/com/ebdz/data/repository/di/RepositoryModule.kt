package com.ebdz.data.repository.di

import com.ebdz.data.repository.TemplateRepositoryImpl
import com.ebdz.domain.repository.TemplateRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Repository dependency injection module.
 */
val repositoryModule = module {
    singleOf(::TemplateRepositoryImpl) bind TemplateRepository::class
}
