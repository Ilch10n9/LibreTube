package com.github.libretube.api.obj

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Subtitle(
    val url: String? = null,
    val mimeType: String? = null,
    val name: String? = null,
    val code: String? = null,
    val autoGenerated: Boolean? = null
)
