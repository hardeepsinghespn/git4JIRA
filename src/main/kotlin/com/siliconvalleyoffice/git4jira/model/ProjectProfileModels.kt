package com.siliconvalleyoffice.git4jira.model

data class ProjectProfileData(var username: String, var projects: List<Projects>)

data class Projects(
        var name: String,
        var versionControlCredentials: VersionControlCredentials,
        var projectManagementCredentials: ProjectManagementCredentials,
        var communicationCredentials: CommunicationCredentials,
        var ciCredentials: CiCredentials)

data class VersionControlCredentials(
        var type: String,
        var username: String,
        var password: String
)

data class ProjectManagementCredentials(
        var type: String,
        var username: String,
        var password: String
)

data class CommunicationCredentials(
        var type: String,
        var username: String,
        var password: String
)

data class CiCredentials(
        var type: String,
        var username: String,
        var password: String
)

