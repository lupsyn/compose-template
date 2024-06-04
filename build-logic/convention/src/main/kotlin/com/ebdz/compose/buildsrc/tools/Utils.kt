package com.ebdz.compose.buildsrc.tools

import com.android.build.api.dsl.CommonExtension

object Utils {

    fun CommonExtension<*, *, *, *, *, *>.excludeFromPackagingOptions(vararg toExclude: String) {
        packaging {
            toExclude.forEach { resources.excludes.add(it) }
        }
    }
}
