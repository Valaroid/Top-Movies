plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.hiltPlugin)

}

android {
    namespace = "com.example.topmovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.topmovies"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
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

    buildFeatures{
        viewBinding=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //My App library

    //Navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    //Room components
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    //Extensions and coroutines for room
    implementation (libs.androidx.room.ktx)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //Gson
    implementation (libs.gson)
    //OkHTTP client
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    //Dagger - Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    kapt (libs.androidx.hilt.compiler)

    //Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //Image Loading
    implementation (libs.coil)

    //Lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

    //DataStore
    implementation (libs.androidx.datastore.preferences)

    //Dynamic sizes
    implementation (libs.dynamicsizes)

    //other
    implementation (libs.circleindicator)








}