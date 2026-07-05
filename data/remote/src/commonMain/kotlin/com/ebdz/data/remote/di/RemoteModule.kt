package com.ebdz.data.remote.di

import com.ebdz.data.remote.network.ApiClient
import com.ebdz.data.remote.network.TemplateApi
import com.ebdz.data.remote.network.TemplateApiImpl
import org.koin.dsl.module

/**
 * Remote data-source dependency injection module.
 *
 * @param apiBaseUrl base URL for [ApiClient]; supplied by the app (e.g. from build config).
 * @param enableLogging whether Ktor's request/response logging plugin is installed.
 */
fun remoteModule(apiBaseUrl: String, enableLogging: Boolean = false) = module {
    single { ApiClient(baseUrl = apiBaseUrl, enableLogging = enableLogging) }
    single<TemplateApi> { TemplateApiImpl(get()) }
}
