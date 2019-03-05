package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GitRepository {

    @GET("/")
    fun authenticate(): Single<String>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Single<User>
}