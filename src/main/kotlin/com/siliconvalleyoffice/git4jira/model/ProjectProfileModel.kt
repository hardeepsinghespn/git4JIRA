package com.siliconvalleyoffice.git4jira.model

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.service.*
import com.siliconvalleyoffice.git4jira.util.API_V3
import com.siliconvalleyoffice.git4jira.util.GITHUB_PUBLIC_BASE_URL
import com.siliconvalleyoffice.git4jira.util.SLASH
import com.siliconvalleyoffice.git4jira.util.prepareAPIV3Url

//"android", "fc211414d7a62764b7890ac963ec5338f199395c"

/**
 * User Config Data
 */
data class UserConfig(
        var username: String = "Test User",
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
)

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
        var valid: Boolean = false
) {
    fun apiUrl() = if(gitType?.isEnterprise() == true) baseUrl?.prepareAPIV3Url() else baseUrl ?: GITHUB_PUBLIC_BASE_URL
}




