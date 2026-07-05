package com.ebdz.libraries.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

/**
 * Installs [dispatcher] as `Dispatchers.Main` for a test and resets it afterward. There's no
 * common equivalent of JUnit4's `@Rule`, so call [setUp]/[tearDown] from `@BeforeTest`/
 * `@AfterTest` in `commonTest`.
 */
@ExperimentalCoroutinesApi
class MainDispatcherRule(private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) {

    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    fun tearDown() {
        Dispatchers.resetMain()
    }
}
