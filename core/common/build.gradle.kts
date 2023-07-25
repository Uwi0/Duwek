plugins {
    id("kakapo.android.library")
    id("kakapo.android.library.jacoco")
    id("kakapo.android.hilt")
}

android {
    namespace = "com.kakapo.common"
}

dependencies {
    api(project(":logging"))
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}