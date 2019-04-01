package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.*
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

    fun validate(baseUrl: String, token: String): Single<BoardResponse>

    fun allBoards(startAt: Int): Single<BoardResponse>

    fun boardByName(boardName: String): Single<BoardResponse>

    fun boardByID(boardId: String): Single<Board>

    fun boardEpics(boardId: String): Single<EpicResponse>

    fun boardIssues(boardId: String): Single<IssuesResponse>

    fun boardEpicIssues(boardId: String, epicId: String): Single<IssuesResponse>
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