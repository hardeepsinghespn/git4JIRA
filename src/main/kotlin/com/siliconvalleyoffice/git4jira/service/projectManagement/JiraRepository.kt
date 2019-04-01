package com.siliconvalleyoffice.git4jira.service.projectManagement

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.model.Board
import com.siliconvalleyoffice.git4jira.model.BoardResponse
import com.siliconvalleyoffice.git4jira.model.EpicResponse
import com.siliconvalleyoffice.git4jira.model.IssuesResponse
import io.reactivex.Single
import retrofit2.http.*

interface JiraRepository {

    @GET
    fun validate(@Url baseUrl: String, @Header(AUTHORIZATION_HEADER) token: String): Single<BoardResponse>

    @GET("board")
    fun allBoards(@Query("startAt") startAt: Int): Single<BoardResponse>

    @GET("board")
    fun boardByName(@Query("name") boardName: String): Single<BoardResponse>

    @GET("board/{boardId}")
    fun boardByID(@Path("boardId") boardId: String): Single<Board>

    @GET("board/{boardId}/epic")
    fun boardEpics(@Path("boardId") boardId: String): Single<EpicResponse>

    @GET("board/{boardId}/issue")
    fun boardIssues(@Path("boardId") boardId: String): Single<IssuesResponse>

    @GET("board/{boardId}/epic/{epicId}/issue")
    fun boardEpicIssues(@Path("boardId") boardId: String, @Path("epicId") epicId: String): Single<IssuesResponse>
}