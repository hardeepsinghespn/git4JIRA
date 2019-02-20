package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.util.GIT_ERROR_ICON
import com.siliconvalleyoffice.git4jira.util.JIRA_ERROR_ICON
import com.siliconvalleyoffice.git4jira.util.SLACK_ERROR_ICON
import com.siliconvalleyoffice.git4jira.util.TEAM_CITY_ERROR_ICON
import com.siliconvalleyoffice.git4jira.service.communication.SlackService
import com.siliconvalleyoffice.git4jira.service.continuousIntegration.TeamCityService
import com.siliconvalleyoffice.git4jira.service.git.GitHubService
import com.siliconvalleyoffice.git4jira.service.projectManagement.JiraService

/**
 * GitServiceEnum to provide selection and respective services
 * Available: GitHub
 */
enum class GitServiceEnum(val service: GitService) {
    GITHUB(GitHubService("GitHub", GIT_ERROR_ICON))
}

/**
 * ProjectManagementEnum to provide selections and respective services
 * Available: Jira
 */
enum class ProjectManagementEnum(val service: ProjectManagementService) {
    JIRA(JiraService("Jira", JIRA_ERROR_ICON))
}

/**
 * CommunicationEnum to provide selections and respective services
 * Available: Slack
 */
enum class CommunicationEnum(val service: CommunicationService?) {
    NONE(null),
    SLACK(SlackService("Slack", SLACK_ERROR_ICON))
}

/**
 * ContinuousIntegrationEnum to provide selections and respective services
 * Available: Team City
 */
enum class ContinuousIntegrationEnum(val service: ContinuousIntegrationService?) {
    NONE(null),
    TEAM_CITY(TeamCityService("Team City", TEAM_CITY_ERROR_ICON))
}
