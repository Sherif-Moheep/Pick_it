import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
    id("com.google.gms.google-services")
}


val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

android {
    namespace = "com.hitech.pickit"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.hitech.pickit"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            type = "String",
            name = "TMDB_BEARER_TOKEN",
            value = "\"${localProperties.getProperty("TMDB_BEARER_TOKEN", "")}\""
        )
    }

    buildTypes {

        debug {
        }
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
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.9.5")
    implementation("androidx.compose.foundation:foundation:1.7.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.play.services.auth)
    implementation(libs.androidx.compose.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation("androidx.compose.runtime:runtime:1.6.7")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material3:material3:1.5.0-alpha09")

    //Icon
    implementation("androidx.compose.material:material-icons-extended:1.7.7")
    //Coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    ksp("com.google.dagger:hilt-android-compiler:2.57.2")
    ksp("androidx.hilt:hilt-compiler:1.3.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.2.1")
    implementation("com.squareup.okhttp3:logging-interceptor:5.2.1")
    // Kotlinx Serialization Converter for Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.2.1")
    implementation("com.squareup.okhttp3:logging-interceptor:5.2.1")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    // Coroutines - Deferred adapter
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.paging:paging-runtime-ktx:3.3.6")
    implementation("androidx.paging:paging-compose:3.3.6")
    // Room
    implementation("androidx.room:room-runtime:2.7.2")
    annotationProcessor("androidx.room:room-compiler:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    //browser
    implementation("androidx.browser:browser:1.8.0")
    //Palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("com.airbnb.android:lottie-compose:6.4.0")
    // Data Store
    implementation("androidx.datastore:datastore-preferences:1.1.7")


    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.appcompat:appcompat-resources:1.7.1")

    // --- 1. The Modern Google Sign-In (Credential Manager) ---
    // The core library for the new sign-in flow
    implementation("androidx.credentials:credentials:1.3.0")
    // support for Google Sign-In specifically
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    // Google ID library to parse the result (get the token)
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // --- 2. Firebase Authentication ---
    // It's best to use the Firebase BoM (Bill of Materials) to manage versions
    implementation(platform("com.google.firebase:firebase-bom:34.6.0"))
    // The actual Auth library
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-analytics")
    // --- 3. Essential Helper (Highly Recommended) ---
    // Allows you to use ".await()" on Firebase tasks instead of callbacks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // fireStore
    implementation("com.google.firebase:firebase-firestore")
}