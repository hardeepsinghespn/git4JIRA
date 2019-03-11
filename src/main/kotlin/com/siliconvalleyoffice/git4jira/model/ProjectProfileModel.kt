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

data class GitServiceConfig(var gitServiceEnum: GitServiceEnum, var credentials: Credentials = Credentials())
data class ProjectManagementServiceConfig(var projectManagementEnum: ProjectManagementEnum, var credentials: Credentials = Credentials())
data class CommunicationServiceConfig(var communicationEnum: CommunicationEnum, var credentials: Credentials = Credentials())
data class ContinuousIntegrationServiceConfig(var continuousIntegrationEnum: ContinuousIntegrationEnum, var credentials: Credentials = Credentials())

data class Credentials(var username: String = EMPTY, var password: String = EMPTY, var isValid: Boolean = false)

