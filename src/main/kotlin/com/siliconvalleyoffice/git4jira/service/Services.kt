package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.model.User
import io.reactivex.Single

/**
 * Provide Git Services
 * Available: GitHub
 */
interface GitService {

    fun validate(baseUrl: String, token: String): Single<GitAuthorizations>

    fun authenticate(): Single<GitAuthorizations>

    fun getUser(username: String): Single<User>
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