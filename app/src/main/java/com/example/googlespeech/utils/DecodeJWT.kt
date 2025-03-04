package com.example.googlespeech.utils

import android.util.Base64
import org.json.JSONObject

fun decodeJWT(token: String?): Map<String, Any>? {
    if (token.isNullOrBlank()) return null

    return try {
        val parts = token.split(".")
        if (parts.size != 3) return null

        val payload = String(Base64.decode(parts[1], Base64.URL_SAFE))
        val jsonObject = JSONObject(payload)

        val resultMap = mutableMapOf<String, Any>()
        jsonObject.keys().forEach { key ->
            resultMap[key] = jsonObject.get(key)
        }

        resultMap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
