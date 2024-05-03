object Dependencies {

    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4"
    const val composeTestBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val lifecycleRuntimeCompose =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
    const val lifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"

    const val glide = "com.github.bumptech.glide:compose:${Versions.glide}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConvertorGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

}