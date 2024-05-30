plugins {
    id("com.android.application") version "8.4.1"
    id("com.google.gms.google-services")
}
android {
    namespace = "com.ispc.lemone"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ispc.lemone"
        minSdk = 26  // Cambiar de 24 a 26
        targetSdk = 33
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
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("net.sourceforge.jexcelapi:jxl:2.6.12")
}