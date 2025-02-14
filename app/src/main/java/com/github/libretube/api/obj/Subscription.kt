package com.github.libretube.api.obj

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Subscription(
    var url: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var verified: Boolean? = null
)
