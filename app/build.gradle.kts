plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.thinkzy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.thinkzy"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
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
}

dependencies {

    implementation(libs.konfetti.xml)

    // Glide for image loading
    implementation(libs.github.glide)

    // Firebase BOM to manage Firebase versions
    implementation(platform(libs.firebase.bom))

    // Firebase FireStore
    implementation(libs.com.google.firebase.firebase.firestore11)

    // Firebase Auth
    implementation(libs.firebase.auth.v2231)
    implementation(libs.google.firebase.auth)

    // AndroidX Credential Manager
    implementation(libs.androidx.credentials)

    // Google Play Services Auth (for Credential Manager + Google sign-in)
    implementation (libs.androidx.credentials.play.services.auth)

    // Google Sign-In
    implementation(libs.play.services.auth)
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    implementation(libs.lottie)

    implementation(libs.material.v1110)
    implementation(libs.androidx.viewpager2)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}