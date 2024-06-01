plugins {
    id("com.android.application") version "8.4.1"
    id("com.google.gms.google-services")
    kotlin("android") version "1.8.20"
}

android {
    namespace = "com.ispc.lemone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ispc.lemone"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildToolsVersion = "30.0.3"

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.register<Wrapper>("wrapper") {
    gradleVersion = "5.6.4"
}

tasks.register("prepareKotlinBuildScriptModel"){}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("net.sourceforge.jexcelapi:jxl:2.6.12")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.activity:activity:1.8.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
