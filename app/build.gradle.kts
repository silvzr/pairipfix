plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.android.pairipfix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.pairipfix"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Riduce le dimensioni mantenendo solo densità essenziali
        resConfigs("en", "xxhdpi")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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

    // Ottimizzazioni per le dimensioni
    packagingOptions {
        resources {
            excludes += listOf(
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/*.RSA",
                "META-INF/*.SF",
                "META-INF/*.DSA",
                "DebugProbesKt.bin",
                "kotlin-tooling-metadata.json"
            )
        }
        jniLibs {
            useLegacyPackaging = false
        }
    }

    bundle {
        density {
            enableSplit = false
        }
        abi {
            enableSplit = false
        }
        language {
            enableSplit = false
        }
    }
}

dependencies {
    // Rimosso material design e appcompat - non necessari per un modulo Xposed
    compileOnly("de.robv.android.xposed:api:82")
}