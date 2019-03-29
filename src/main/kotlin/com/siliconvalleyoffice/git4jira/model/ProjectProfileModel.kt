package com.siliconvalleyoffice.git4jira.model

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.service.*
import com.siliconvalleyoffice.git4jira.util.GITHUB_PUBLIC_BASE_URL
import com.siliconvalleyoffice.git4jira.util.JIRA_API_BASE_URL
import com.siliconvalleyoffice.git4jira.util.prepareGitApiUrl
import com.siliconvalleyoffice.git4jira.util.prepareJiraApiUrl

//"android", "fc211414d7a62764b7890ac963ec5338f199395c"

/**
 * GitHubUserResponse Config Data
 */
data class UserConfig(
        var username: String = "Test UserName",
        var encryptionPhrase: String = "Test Phrase",
        var encryptionKey: String = "TestKey",
        var lastSelection: String = "",
        var project: MutableList<Project> = emptyList<Project>().toMutableList()
)

data class Project(
        var name: String,
        var logo: String,
        var projectRootDirectoryPath: String? = EMPTY,
        var gitServiceConfig: GitServiceConfig?,
        var projectManagementServiceConfig: ProjectManagementServiceConfig?,
        var communicationServiceConfig: CommunicationServiceConfig?,
        var continuousIntegrationServiceConfig: ContinuousIntegrationServiceConfig?
)

data class GitServiceConfig(
        var gitServiceEnum: GitServiceEnum,
        var requestInfo: RequestInfo? = RequestInfo()
) {
    fun gitService() = gitServiceEnum.serviceFactoryFunction(requestInfo)
}

data class ProjectManagementServiceConfig(
        var projectManagementEnum: ProjectManagementEnum,
        var requestInfo: RequestInfo? = RequestInfo()
) {
    fun jiraService() = projectManagementEnum.serviceFactoryFunction(requestInfo)
}

data class CommunicationServiceConfig(
        var communicationEnum: CommunicationEnum,
        var requestInfo: RequestInfo? = RequestInfo()
)

data class ContinuousIntegrationServiceConfig(
        var continuousIntegrationEnum: ContinuousIntegrationEnum,
        var requestInfo: RequestInfo? = RequestInfo()
)

data class RequestInfo(
        var gitType: GitType? = null,
        var baseUrl: String? = null,
        var username: String = EMPTY,
        var password: String = EMPTY,
        var boardId: String = EMPTY,
        var credentialsValid: Boolean = false,
        var boardValid: Boolean = false
) {
    fun gitApiUrl() = if (gitType?.isEnterprise() == true) baseUrl?.prepareGitApiUrl() else baseUrl ?: GITHUB_PUBLIC_BASE_URL

    fun jiraApiUrl() = baseUrl?.prepareJiraApiUrl()
}




