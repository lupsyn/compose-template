package com.ebdz.features.template.di

import com.ebdz.features.template.presentation.TemplateReducer
import com.ebdz.features.template.presentation.TemplateViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val templateModule = module {
    factoryOf(::TemplateReducer)
    viewModelOf(::TemplateViewModel)
}
