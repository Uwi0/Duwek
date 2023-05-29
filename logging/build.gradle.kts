plugins {
    id("kakapo.android.library")
}


android {
    namespace = "com.kakapo.logging"
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("Boolean", "DEBUG_MODE", "true")
        }
        release {
            isMinifyEnabled = true
            buildConfigField("Boolean", "DEBUG_MODE", "true")
        }
    }
}

dependencies{
    implementation(libs.timber.logging)
}
