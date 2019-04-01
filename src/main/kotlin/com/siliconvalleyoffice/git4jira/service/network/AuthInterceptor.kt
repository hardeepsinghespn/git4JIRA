package com.siliconvalleyoffice.git4jira.service.network

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.constant.NO_AUTHENTICATION_HEADER
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Credentials as OkHttpCredentials

class AuthInterceptor(userName: String, password: String) : Interceptor {

    private var authToken = OkHttpCredentials.basic(userName, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        if (request.header(AUTHORIZATION_HEADER) == null && request.header(NO_AUTHENTICATION_HEADER) == null) {
            requestBuilder.addHeader(AUTHORIZATION_HEADER, authToken)
        }

        return chain.proceed(requestBuilder.build())
    }
}