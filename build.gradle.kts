// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.agp apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false
}