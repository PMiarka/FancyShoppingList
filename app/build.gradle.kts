import java.io.FileInputStream
import java.util.*

plugins {
    id("kotlin-android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("jacoco")
    id("jacoco-report")
}

android {
    compileSdk = 33

    defaultConfig {
        val currentVersionCode =
            if (System.getenv("CI") != null) {
                getSecretProperty("ciVersionCode").toInt()
            } else {
                1
            }
        applicationId = "com.fansymasters.shoppinglist"
        minSdk = 27
        targetSdk = 33
        versionCode = currentVersionCode
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    val apiKey = getSecretProperty("apiUrl")
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            buildConfigField("String", "API_URL", apiKey)
            isTestCoverageEnabled = true
            enableUnitTestCoverage = true
        }
        getByName("release") {
            isMinifyEnabled = true
            buildConfigField("String", "API_URL", apiKey)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testCoverage {
        jacocoVersion = "0.8.8"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}
val composeVersion = "1.3.1"

dependencies {
    composeImplementation()
    composeNavigationImplementation()
    accompanistImplementation()

    androidxCoreKtxImplementation()

    lifecycleImplementation()

    materialsImplementation()

    // coroutines
    coroutinesImplementation()

    // WorkManager
    workManagerImplementation()

    // play services
    playServicesImplementation()

    // retrofit
    retrofitImplementation()

    // okhttp
    okHttpImplementation()

    // gson
    gsonImplementation()

    // hilt
    hiltImplementation()

    // Room
    roomImplementation()

    // Security Crypto
    securityCryptoImplementation()

    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("app.cash.turbine:turbine:0.12.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")


    uiTestImplementation()

    composeDebugImplementation()
}

fun DependencyHandlerScope.composeImplementation() {
    val activityComposeVersion = "1.6.1"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
}

fun DependencyHandlerScope.composeNavigationImplementation() {
    val composeNavigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$composeNavigationVersion")
}

fun DependencyHandlerScope.accompanistImplementation() {
    val accompanistVersion = "0.28.0"
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-flowlayout:$accompanistVersion")
}

fun DependencyHandlerScope.androidxCoreKtxImplementation() {
    val androidxCoreVersion = "1.9.0"
    implementation("androidx.core:core-ktx:$androidxCoreVersion")
}

fun DependencyHandlerScope.lifecycleImplementation() {
    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
}

fun DependencyHandlerScope.materialsImplementation() {
    val materials3Version = "1.1.0-alpha02"
    val materialsVersion = "1.3.1"
    implementation("androidx.compose.material3:material3:$materials3Version")
    implementation("androidx.compose.material:material:$materialsVersion")
}

fun DependencyHandlerScope.coroutinesImplementation() {
    val coroutinesVersion = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
}

fun DependencyHandlerScope.workManagerImplementation() {
    val workManagerVersion = "2.7.1"
    implementation("androidx.work:work-runtime-ktx:$workManagerVersion")

}

fun DependencyHandlerScope.playServicesImplementation() {
    val gsmVersion = "20.3.0"
    val firebaseBomVersion = "31.0.2"
    implementation("com.google.android.gms:play-services-auth:$gsmVersion")
    implementation("com.google.firebase:firebase-bom:$firebaseBomVersion")
}

fun DependencyHandlerScope.retrofitImplementation() {
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

}

fun DependencyHandlerScope.okHttpImplementation() {
    val okHttpBomVersion = "4.10.0"
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okHttpBomVersion"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
}

fun DependencyHandlerScope.gsonImplementation() {
    val gsonVersion = "2.9.0"
    implementation("com.google.code.gson:gson:$gsonVersion")
}

fun DependencyHandlerScope.roomImplementation() {
    val roomVersion = "2.4.3"

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
}

fun DependencyHandlerScope.composeDebugImplementation() {
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}

fun DependencyHandlerScope.securityCryptoImplementation() {
    implementation("androidx.security:security-crypto:1.0.0")
}

fun DependencyHandlerScope.hiltImplementation() {
    val hiltVersion = "2.44.1"
    val androidxHiltVersion = "1.0.0"
    implementation("androidx.hilt:hilt-navigation-compose:$androidxHiltVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-work:$androidxHiltVersion")
    kapt("androidx.hilt:hilt-compiler:$androidxHiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
}

fun DependencyHandlerScope.uiTestImplementation() {
    val extJunitVersion = "1.1.4"
    val espressoCore = "3.5.0"
    androidTestImplementation("androidx.test.ext:junit:$extJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCore")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
}

fun getSecretProperty(key: String): String {
    System.out.println("getSecretProperty: $key")
    val fis = FileInputStream("$rootDir/secret.properties")
    val prop = Properties()
    prop.load(fis)
    return prop.getProperty(key)
}
