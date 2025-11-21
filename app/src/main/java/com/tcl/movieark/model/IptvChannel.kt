package com.tcl.movieark.model

data class IptvChannel(
    val name: String,
    val url: String,
    val group: String? = null,
    val logo: String? = null
)
