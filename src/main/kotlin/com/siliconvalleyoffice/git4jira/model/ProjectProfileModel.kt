package com.siliconvalleyoffice.git4jira.model

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.service.*

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
        var gitService: GitServiceConfig?,
        var projectManagementService: ProjectManagementServiceConfig?,
        var communicationService: CommunicationServiceConfig?,
        var continuousIntegrationService: ContinuousIntegrationServiceConfig?
)

data class GitServiceConfig(
        var gitServiceEnum: GitServiceEnum,
        var gitType: GitType? = null,
        var requestInfo: RequestInfo? = RequestInfo()
)

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
        var baseUrl: String = EMPTY,
        var username: String = EMPTY,
        var password: String = EMPTY,
        var valid: Boolean = false
)




