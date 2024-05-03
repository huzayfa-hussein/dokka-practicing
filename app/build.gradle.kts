plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.dokka") version Versions.dokka
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = Configs.sourceCompatibility
        targetCompatibility = Configs.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.ktx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    testImplementation(Dependencies.composeJunit)
    androidTestImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(platform(Dependencies.composeTestBom))
    androidTestImplementation(Dependencies.composeJunit)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)

    // viewModel
    implementation(Dependencies.lifecycleRuntimeCompose)
    implementation(Dependencies.lifecycleViewModelCompose)

    //Glide
    implementation(Dependencies.glide)

    // hilt

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltKapt)

    // Network
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConvertorGson)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLoggingInterceptor)
}

kapt {
    correctErrorTypes = true
}