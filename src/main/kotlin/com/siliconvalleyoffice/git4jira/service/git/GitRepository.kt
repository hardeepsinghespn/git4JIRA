package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.constant.NO_AUTHENTICATION_HEADER
import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.model.User
import com.siliconvalleyoffice.git4jira.util.GITHUB_API_BASE_URL
import io.reactivex.Single
import retrofit2.http.*

interface GitRepository {

    @GET
    @Headers("$NO_AUTHENTICATION_HEADER: true")
    fun validateBaseUrl(@Url baseUrl: String): Single<String>

    @GET
    fun validate(@Header(AUTHORIZATION_HEADER) token: String,
                 @Url baseUrl: String = GITHUB_API_BASE_URL): Single<GitAuthorizations>

    @GET
    fun authenticate(@Url baseUrl: String = GITHUB_API_BASE_URL): Single<GitAuthorizations>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Single<User>
}