package org.example.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object APIClient {
    private var logEnabled = false

    val httpClient = HttpClient(CIO) {

        install(HttpTimeout) {
            socketTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
        }

        if (logEnabled) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Logger Msg: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }

        install(ContentNegotiation) {
            json(json = Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    fun enableLog(isLogVisible: Boolean) {
        logEnabled = isLogVisible
    }
}
