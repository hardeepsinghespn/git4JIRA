package com.siliconvalleyoffice.git4jira.model

data class Configuration(
        var version: String,
        var projectProfileOptions: ProjectProfileOptions
)

data class ProjectProfileOptions(
        var versionControl: List<String>,
        var projectManagement: List<String>,
        var communication: List<String>,
        var continuousIntegration: List<String>
)