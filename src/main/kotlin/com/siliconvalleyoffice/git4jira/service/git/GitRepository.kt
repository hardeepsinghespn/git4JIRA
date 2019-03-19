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
    fun validate(@Url baseUrl: String, @Header(AUTHORIZATION_HEADER) token: String): Single<GitAuthorizations>

    @GET("/")
    fun authenticate(): Single<GitAuthorizations>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Single<User>
}