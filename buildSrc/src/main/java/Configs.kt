import org.gradle.api.JavaVersion

/**
 * Configs
 *
 * @constructor Create empty Configs
 */
object Configs {

    const val minSdk = 24
    const val compileSdk = 34
    const val targetSdk = 34
    const val versionCode = 1
    const val versionName = "1"
    const val namespace = "mobi.foo.dokkapracticing"
    const val applicationId = "mobi.foo.dokkapracticing"
    const val jvmTarget = "1.8"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
}