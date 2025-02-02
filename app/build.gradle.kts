plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.esmanureral.filmjc"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.esmanureral.filmjc"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")

    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.0")

    implementation("androidx.compose.ui:ui-graphics:1.5.0")
    implementation("androidx.compose.material3:material3:1.2.0")  // Material 3 kütüphanesini güncelledim

    // Jetpack Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Coil (Resim Yükleme)
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Material Icons
    implementation("androidx.compose.material:material-icons-core:1.6.5")
    implementation("androidx.compose.material:material-icons-extended:1.6.5")
}
