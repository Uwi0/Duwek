plugins {
    id("kotlin")
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    api(libs.serpro69.faker)
}