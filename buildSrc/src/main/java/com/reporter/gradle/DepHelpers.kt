package dz.nexatech.reporter.gradle

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addRoomApi(transient: Boolean = false) {
    add(apiOrImpl(transient), "androidx.room:room-runtime:${Version.ROOM}")
    add(apiOrImpl(transient), "androidx.room:room-ktx:${Version.ROOM}")
    add(apiOrImpl(transient), "androidx.room:room-paging:${Version.ROOM}")
    add(apiOrImpl(transient), "androidx.room:room-guava:${Version.ROOM}")
}

fun DependencyHandler.addRoomCompiler() {
    add("kapt", "androidx.room:room-compiler:${Version.ROOM}")
}

fun DependencyHandler.addCoroutines(version: String = Version.COROUTINES, transient: Boolean = false) {
    add(apiOrImpl(transient), "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version")
    add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version")
}

fun DependencyHandler.addHilt(version: String) {
    addHiltDependencies(version)
    addHiltCompiler(version)
}

fun DependencyHandler.addHiltDependencies(version: String, transient: Boolean = false) {
    add(apiOrImpl(transient), "com.google.dagger:hilt-android:$version")
}

fun DependencyHandler.addHiltCompiler(version: String) {
    add("kapt", "com.google.dagger:hilt-android-compiler:$version")
}

fun DependencyHandler.addLifecycle(transient: Boolean = false) {
    add(apiOrImpl(transient), "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}")
    add(apiOrImpl(transient), "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.LIFECYCLE_COMPOSE}")
    add(apiOrImpl(transient), "androidx.lifecycle:lifecycle-common-java8:${Version.LIFECYCLE}")
    add(apiOrImpl(transient), "androidx.lifecycle:lifecycle-service:${Version.LIFECYCLE}")
    add(apiOrImpl(transient), "androidx.lifecycle:lifecycle-process:${Version.LIFECYCLE}")
    add(apiOrImpl(transient, "debug"),"androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.LIFECYCLE}")
    add(apiOrImpl(transient, "debug"),"androidx.lifecycle:lifecycle-runtime:${Version.LIFECYCLE}")
    add(apiOrImpl(transient, "debug"), "androidx.customview:customview-poolingcontainer:${Version.CUSTOM_VIEW_POOLING}")
    add(apiOrImpl(transient, "test"), "androidx.arch.core:core-testing:${Version.CORE_ARCH}")
    add(apiOrImpl(transient, "test"), "androidx.lifecycle:lifecycle-runtime-testing:${Version.LIFECYCLE}")
}

fun apiOrImpl(transient: Boolean) = if (transient) "api" else "implementation"
fun apiOrImpl(transient: Boolean, prefix: String) = if (transient) prefix + "Api" else prefix + "Implementation"

fun DependencyHandler.addCommonTestDependencies(appModule: Boolean = true) {
//    add("testImplementation","com.google.dagger:hilt-android-testing:${Version.HILT}")
//    add("kaptTest","com.google.dagger:hilt-compiler:${Version.HILT}")
//    add("testImplementation", "androidx.room:room-testing:${Version.ROOM}")
    add("testImplementation", "junit:junit:${Version.JUNIT4}")
    if (appModule) {
        add("debugImplementation", "androidx.compose.ui:ui-test-manifest:${Version.Compose.UI}")
    }

    add("testImplementation", "com.google.truth:truth:${Version.TRUTH}")
}

fun DependencyHandler.addCommonAndroidTestDependencies() {
//    add("androidTestImplementation","com.google.dagger:hilt-android-testing:${Version.HILT}")
//    add("kaptAndroidTest","com.google.dagger:hilt-compiler:${Version.HILT}")
    add("androidTestImplementation", "androidx.test.ext:junit:${Version.Test.EXT_JUNIT}")
    add("androidTestImplementation", "androidx.test.ext:junit-ktx:${Version.Test.EXT_JUNIT}")

    add("androidTestImplementation", "androidx.test.espresso:espresso-core:${Version.Test.ESPRESSO}")
    add("androidTestImplementation", "androidx.compose.ui:ui-test-junit4:${Version.Compose.UI}")

    add("androidTestImplementation", "com.google.truth:truth:${Version.TRUTH}")
    add("androidTestImplementation", "androidx.test.ext:junit-ktx:${Version.Test.EXT_JUNIT}")
    add("androidTestImplementation", "androidx.arch.core:core-testing:${Version.CORE_ARCH}")
}