package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.model.GitHubUserResponse
import io.reactivex.Single

/**
 * Provide Git Services
 * Available: GitHub
 */
interface GitService {

    fun validate(baseUrl: String, token: String): Single<GitHubUserResponse>

    fun authenticate(): Single<GitHubUserResponse>

    fun getUser(username: String): Single<GitHubUserResponse>
}

/**
 * Provide Project Management Services
 * Available: Jira
 */
interface ProjectManagementService {

    fun validate(baseUrl: String, token: String)

    fun allBoards(startAt: Int)

    fun boardByName(boardName: String)

    fun boardByID(boardId: String)

    fun boardEpics(boardId: String)

    fun boardIssues(boardId: String)

    fun boardEpicIssues(boardId: String, epicId: String)
}

/**
 * Provide Communication Services
 * Available: Slack
 */
interface CommunicationService {

}

/**
 * Provide Continuous Integration Services
 * Available: TeamCity
 */
interface ContinuousIntegrationService {

}