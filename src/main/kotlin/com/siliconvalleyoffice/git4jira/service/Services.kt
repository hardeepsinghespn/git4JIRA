package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.User
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Provide Git Services
 * Available: GitHub
 */
interface GitService : Service.BaseService {

    fun authenticate(): Single<String>

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