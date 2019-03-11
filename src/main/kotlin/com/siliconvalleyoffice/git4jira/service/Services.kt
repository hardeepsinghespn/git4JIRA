package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.model.User
import com.siliconvalleyoffice.git4jira.service.git.GitAuthInterceptor
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Provide Git Services
 * Available: GitHub
 */
interface GitService : Service.BaseService {

    fun validate(token: String): Single<GitAuthorizations>

    fun authenticate(): Single<GitAuthorizations>

    fun getUser(username: String): Single<User>
}

/**
 * Provide Project Management Services
 * Available: Jira
 */
interface ProjectManagementService : Service.BaseService {

}

/**
 * Provide Communication Services
 * Available: Slack
 */
interface CommunicationService : Service.BaseService {

}

/**
 * Provide Continuous Integration Services
 * Available: TeamCity
 */
interface ContinuousIntegrationService : Service.BaseService {

}