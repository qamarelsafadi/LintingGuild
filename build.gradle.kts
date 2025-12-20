// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

gradle.projectsEvaluated {
    subprojects {
        val configureBuildLintDependency = Action<Plugin<*>> {
            tasks.named("build").configure {
                dependsOn("lint")
            }
        }

        plugins.withId("com.android.application", configureBuildLintDependency)
        plugins.withId("com.android.library", configureBuildLintDependency)
    }
}