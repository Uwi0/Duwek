plugins {
    id("kakapo.android.feature")
    id("kakapo.android.library.compose")
    id("kakapo.android.library.jacoco")
}

android {
    namespace = "com.kakapo.transactions"
}

dependencies {
    implementation(libs.androidx.constraint.layout)
}