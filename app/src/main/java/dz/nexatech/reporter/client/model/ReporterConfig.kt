package dz.nexatech.reporter.client.model

import com.google.common.collect.ImmutableMap
import com.itextpdf.kernel.pdf.CompressionConstants
import dz.nexatech.reporter.util.model.LocalConfig
import dz.nexatech.reporter.util.model.RemoteConfig
import dz.nexatech.reporter.util.model.putLocalConfig
import dz.nexatech.reporter.util.model.putRemoteConfig

val LOCALE_TEMPLATE_PREVIEW_DEBOUNCE = LocalConfig("TEMPLATE_PREVIEW_DEBOUNCE", 500L)
val REMOTE_TEMPLATES_LIST_LOADING_ANIMATION_ENABLED = RemoteConfig("TEMPLATES_LIST_LOADING_ANIMATION_ENABLED", true)
val REMOTE_PDF_RESOURCES_CACHING_ENABLED = RemoteConfig("PDF_RESOURCES_CACHING_ENABLED", false)
val REMOTE_PDF_COMPRESSION_LEVEL = RemoteConfig("PDF_COMPRESSION_LEVEL", CompressionConstants.BEST_COMPRESSION)

val ALL_REMOTE_REPORTER_CONFIGS: ImmutableMap<String, Any> = ImmutableMap.builder<String, Any>()
    .putRemoteConfig(REMOTE_TEMPLATES_LIST_LOADING_ANIMATION_ENABLED)
    .putRemoteConfig(REMOTE_PDF_RESOURCES_CACHING_ENABLED)
    .putRemoteConfig(REMOTE_PDF_COMPRESSION_LEVEL)
    .build()

@Suppress("unused")
val ALL_LOCAL_REPORTER_CONFIGS: ImmutableMap<String, Any> = ImmutableMap.builder<String, Any>()
    .putLocalConfig(LOCALE_TEMPLATE_PREVIEW_DEBOUNCE)
    .build()