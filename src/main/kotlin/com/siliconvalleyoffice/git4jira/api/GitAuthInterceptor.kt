package com.siliconvalleyoffice.git4jira.api

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.constant.NO_AUTHENTICATION_HEADER
import com.siliconvalleyoffice.git4jira.model.Credentials
import okhttp3.Credentials as OkHttpCredentials
import okhttp3.Interceptor
import okhttp3.Response

class GitAuthInterceptor : Interceptor {

    private var authToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder();

        if (request.header(NO_AUTHENTICATION_HEADER) == null) {
            authToken?.let { requestBuilder.addHeader(AUTHORIZATION_HEADER, it) }
        }

        println(request.headers().toString())
        return chain.proceed(requestBuilder.build())
    }

    fun setCredentials(credentials: Credentials?) {
        authToken = OkHttpCredentials.basic(credentials?.username ?: EMPTY, credentials?.password ?: EMPTY)
    }
}