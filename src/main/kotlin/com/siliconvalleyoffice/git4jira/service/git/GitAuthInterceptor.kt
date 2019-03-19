package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.constant.NO_AUTHENTICATION_HEADER
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.GitType
import com.siliconvalleyoffice.git4jira.util.prepareAPIV3Url
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Credentials as OkHttpCredentials

class GitAuthInterceptor : Interceptor {

    private var authToken: String? = null

    private var baseUrl: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        //Update Url
        requestBuilder.url(baseUrl ?: EMPTY)

        //Update Headers
        if (request.header(AUTHORIZATION_HEADER) == null && request.header(NO_AUTHENTICATION_HEADER) == null) {
            authToken?.let { requestBuilder.addHeader(AUTHORIZATION_HEADER, it) }
        }

        return chain.proceed(requestBuilder.build())
    }

    fun updateRequestConfig(gitType: GitType?, requestInfo: RequestInfo?) {
        authToken = OkHttpCredentials.basic(requestInfo?.username ?: EMPTY, requestInfo?.password ?: EMPTY)
        baseUrl = if(gitType?.isEnterprise() == true) requestInfo?.baseUrl?.prepareAPIV3Url() else gitType?.url
    }
}