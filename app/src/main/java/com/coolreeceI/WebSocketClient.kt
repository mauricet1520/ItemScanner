package com.coolreeceI

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString


class WebSocketClient(private val listener: WebSocketListener) {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private lateinit var webSocket: WebSocket

    fun connectWebSocket(url: String) {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun sendTextMessage(message: String) {
        webSocket.send(message)
    }

    fun sendBinaryMessage(data: ByteArray) {
        webSocket.send(ByteString.of(*data))
    }

    fun disconnectWebSocket() {
        webSocket.close(1000, "Goodbye!")
    }
}