package com.kakapo.duwek

@Suppress("unused")
enum class DuwekBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
    BENCHMARK(".benchmark")
}
