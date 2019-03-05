package com.siliconvalleyoffice.git4jira.service.git

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

        val response = chain.proceed(requestBuilder.build())
        val responseBuilder = response.newBuilder()

        println(response.code())
        when(response.code()) {
            401 -> responseBuilder.message("Authentication Error")
            200 -> responseBuilder.message("Authenticated")
        }
        return responseBuilder.build()
    }

    fun setCredentials(credentials: Credentials?) {
        authToken = OkHttpCredentials.basic(credentials?.username ?: EMPTY, credentials?.password ?: EMPTY)
    }
}