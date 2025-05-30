plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.convertcurrency"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.convertcurrency"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.0")

// room
    implementation("androidx.room:room-runtime:2.6.1" )
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    // optional - Test helpers
    testImplementation("androidx.room:room-testing:2.6.1")
// optional - Paging 3 Integration
    implementation ("androidx.room:room-paging:2.6.1")

    implementation ("androidx.work:work-runtime:2.7.1")

}