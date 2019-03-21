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