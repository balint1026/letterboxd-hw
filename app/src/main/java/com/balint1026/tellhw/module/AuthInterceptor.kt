package com.balint1026.tellhw.module

import io.github.cdimascio.dotenv.dotenv
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(): Interceptor {

    private val dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer ${dotenv["API_READ_ACCESS_TOKEN"]}")
        return chain.proceed(request.build())
    }
}