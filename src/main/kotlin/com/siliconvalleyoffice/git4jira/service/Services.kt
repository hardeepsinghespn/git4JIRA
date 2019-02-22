package com.siliconvalleyoffice.git4jira.service

/**
 * Provide Git Services
 * Available: GitHub
 */
interface GitService : Service.BaseService {

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