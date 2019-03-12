package com.siliconvalleyoffice.git4jira.model

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.service.CommunicationEnum
import com.siliconvalleyoffice.git4jira.service.ContinuousIntegrationEnum
import com.siliconvalleyoffice.git4jira.service.GitServiceEnum
import com.siliconvalleyoffice.git4jira.service.ProjectManagementEnum

//"android", "fc211414d7a62764b7890ac963ec5338f199395c"

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
        var projectRootDirectoryPath: String,
        var gitService: GitServiceConfig?,
        var projectManagementService: ProjectManagementServiceConfig?,
        var communicationService: CommunicationServiceConfig?,
        var continuousIntegrationService: ContinuousIntegrationServiceConfig?
)

data class GitServiceConfig(var gitServiceEnum: GitServiceEnum, var requestInfo: RequestInfo? = RequestInfo())
data class ProjectManagementServiceConfig(var projectManagementEnum: ProjectManagementEnum, var requestInfo: RequestInfo? = RequestInfo())
data class CommunicationServiceConfig(var communicationEnum: CommunicationEnum, var requestInfo: RequestInfo? = RequestInfo())
data class ContinuousIntegrationServiceConfig(var continuousIntegrationEnum: ContinuousIntegrationEnum, var requestInfo: RequestInfo? = RequestInfo())

data class RequestInfo(
        var username: String = EMPTY,
        var password: String = EMPTY,
        var credentialsValid: Boolean = false,
        var baseUrl: String = EMPTY,
        var baseUrlValid: Boolean = false
)

