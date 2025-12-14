plugins {
    id("java-library")
    id(libs.plugins.jetbrains.kotlin.jvm.get().pluginId)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.lint.api) // = agp + 23.0.0
    testImplementation(libs.lint.tests)
}

tasks.jar {
    manifest {
        attributes["Lint-Registry-v2"] = "com.qamar.guildlint.issue.HardcodedColorIssue"
    }
}