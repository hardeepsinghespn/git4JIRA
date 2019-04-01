package com.siliconvalleyoffice.git4jira.service.projectManagement

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.model.BoardResponse
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.ProjectManagementService
import com.siliconvalleyoffice.git4jira.service.network.AuthInterceptor
import com.siliconvalleyoffice.git4jira.util.JIRA_API_BASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class JiraService(requestInfo: RequestInfo?) : ProjectManagementService {

    var retrofitBuilder: Retrofit.Builder = Injector.Instance.appComponent.retrofitBuilder()
    private val jiraRepository: JiraRepository

    init {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(requestInfo?.username
                        ?: EMPTY, requestInfo?.password ?: EMPTY))
                .build()

        val retrofit = retrofitBuilder
                .baseUrl(requestInfo?.jiraApiUrl() ?: JIRA_API_BASE_URL)
                .client(okHttpClient)
                .build()

        jiraRepository = retrofit.create(JiraRepository::class.java)
    }

    override fun validate(baseUrl: String, token: String) = jiraRepository.validate(baseUrl, token)

    override fun authenticate() = jiraRepository.allBoards(0)

    override fun allBoards(startAt: Int) = jiraRepository.allBoards(startAt)

    override fun boardByName(boardName: String) = jiraRepository.boardByName(boardName)

    override fun boardByID(boardId: String) = jiraRepository.boardByID(boardId)

    override fun boardEpics(boardId: String) = jiraRepository.boardEpics(boardId)

    override fun boardIssues(boardId: String) = jiraRepository.boardIssues(boardId)

    override fun boardEpicIssues(boardId: String, epicId: String) = jiraRepository.boardEpicIssues(boardId, epicId)
}