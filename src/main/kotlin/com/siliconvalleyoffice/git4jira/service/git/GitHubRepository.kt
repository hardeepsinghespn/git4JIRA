package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.model.GitHubUserResponse
import io.reactivex.Single
import retrofit2.http.*

interface GitHubRepository {

    @GET
    fun validate(@Url baseUrl: String, @Header(AUTHORIZATION_HEADER) token: String): Single<GitHubUserResponse>

    @GET("user")
    fun authenticate(): Single<GitHubUserResponse>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Single<GitHubUserResponse>
}