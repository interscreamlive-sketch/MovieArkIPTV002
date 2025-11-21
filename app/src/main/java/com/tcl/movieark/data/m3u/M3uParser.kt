package com.tcl.movieark.data.m3u

import com.tcl.movieark.model.IptvChannel
import okhttp3.OkHttpClient
import okhttp3.Request

class M3uParser {

    private val http = OkHttpClient()

    @Throws(Exception::class)
    fun loadFromUrl(url: String): List<IptvChannel> {
        val req = Request.Builder().url(url).build()
        http.newCall(req).execute().use { resp ->
            if (!resp.isSuccessful) throw Exception("HTTP error: ${'$'}{resp.code}")
            val body = resp.body?.string() ?: return emptyList()
            return parse(body)
        }
    }

    fun parse(text: String): List<IptvChannel> {
        val result = mutableListOf<IptvChannel>()
        var name: String? = null
        var group: String? = null
        var logo: String? = null

        text.lines().forEach { line ->
            val t = line.trim()
            if (t.startsWith("#EXTINF", ignoreCase = true)) {
                val logoMatch = Regex("tvg-logo=\"(.*?)\"").find(t)
                logo = logoMatch?.groupValues?.getOrNull(1)
                val groupMatch = Regex("group-title=\"(.*?)\"").find(t)
                group = groupMatch?.groupValues?.getOrNull(1)
                name = t.substringAfter(",", missingDelimiterValue = "").ifEmpty { null }
            } else if (t.isNotBlank() && !t.startsWith("#")) {
                result.add(
                    IptvChannel(
                        name = name ?: t,
                        url = t,
                        group = group,
                        logo = logo
                    )
                )
                name = null
                group = null
                logo = null
            }
        }
        return result
    }
}
