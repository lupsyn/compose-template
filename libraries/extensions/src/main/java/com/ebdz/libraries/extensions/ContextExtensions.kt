@file:JvmName("extension-context")

package com.ebdz.libraries.extensions

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import io.github.oshai.kotlinlogging.KotlinLogging

private const val INVALID_VERSION = "x.x.x"
/**
 * Returns the version name of the application.
 *
 * @return the version name of the application.
 */
fun Context.getVersionName(): String {
    var packageInfo: PackageInfo? = null
    packageName.let {
        try {
            packageInfo = packageManager.getPackageInfo(it, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            logger.error(e.localizedMessage)
        }
    }
    return packageInfo?.versionName ?: INVALID_VERSION
}

private val logger = KotlinLogging.logger {}
