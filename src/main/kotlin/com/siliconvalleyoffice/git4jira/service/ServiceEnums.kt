package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.communication.SlackService
import com.siliconvalleyoffice.git4jira.service.continuousIntegration.TeamCityService
import com.siliconvalleyoffice.git4jira.service.git.GitHubService
import com.siliconvalleyoffice.git4jira.service.projectManagement.JiraService
import com.siliconvalleyoffice.git4jira.util.*

/**
 * GitServiceEnum to provide selection and respective services
 * Available: GitHub
 */
enum class GitType(val url: String) {
    PUBLIC(GITHUB_PUBLIC_BASE_URL),
    ENTERPRISE(HTTPS);

    fun isPublic() = equals(PUBLIC)
    fun isEnterprise() = equals(ENTERPRISE)
}

enum class GitServiceEnum(val serviceName: String, val serviceLogo: String, val serviceErrorLog: String, val serviceFactoryFunction: (RequestInfo?) -> GitService) {
    GITHUB(GITHUB_VAL, GIT_ICON, GIT_ERROR_ICON, { requestInfo -> GitHubService(requestInfo) })
}

/**
 * ProjectManagementEnum to provide selections and respective services
 * Available: Jira
 */
enum class ProjectManagementEnum(val serviceName: String, val serviceLogo: String, val serviceErrorLog: String, val serviceFactoryFunction: (RequestInfo?) -> ProjectManagementService) {
    JIRA(JIRA_VAL, JIRA_ICON, JIRA_ERROR_ICON, { requestInfo -> JiraService(requestInfo) })
}

/**
 * CommunicationEnum to provide selections and respective services
 * Available: Slack
 */
enum class CommunicationEnum(val serviceName: String?, val serviceLogo: String?, val serviceErrorLog: String?, val serviceFactoryFunction: (RequestInfo?) -> CommunicationService?) {
    NONE(null, null, null, { null }),
    SLACK(SLACK_VAL, SLACK_ICON, SLACK_ERROR_ICON, { requestInfo -> SlackService(requestInfo) })
}

/**
 * ContinuousIntegrationEnum to provide selections and respective services
 * Available: Team City
 */
enum class ContinuousIntegrationEnum(val serviceName: String?, val serviceLogo: String?, val serviceErrorLog: String?, val serviceFactoryFunction: (RequestInfo?) -> ContinuousIntegrationService?) {
    NONE(null, null, null, { null }),
    TEAM_CITY(TEAM_CITY_VAL, TEAM_CITY_ICON, TEAM_CITY_ERROR_ICON, { requestInfo -> TeamCityService(requestInfo) })
}
