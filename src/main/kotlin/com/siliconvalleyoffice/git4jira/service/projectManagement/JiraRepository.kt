package com.siliconvalleyoffice.git4jira.service.projectManagement

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import retrofit2.http.*

interface JiraRepository {

    @GET
    fun validate(@Url baseUrl: String, @Header(AUTHORIZATION_HEADER) token: String)

    @GET("board")
    fun allBoards(@Query("startAt") startAt: Int)

    @GET("board")
    fun boardByName(@Query("name") boardName: String)

    @GET("board/{boardId}")
    fun boardByID(@Path("boardId") boardId: String)

    @GET("board/{boardId}/epic")
    fun boardEpics(@Path("boardId") boardId: String)

    @GET("board/{boardId}/issue")
    fun boardIssues(@Path("boardId") boardId: String)

    @GET("board/{boardId}/epic/{epicId}/issue")
    fun boardEpicIssues(@Path("boardId") boardId: String, @Path("epicId") epicId: String)
}