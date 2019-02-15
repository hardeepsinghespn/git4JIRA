package com.siliconvalleyoffice.git4jira.models

import com.siliconvalleyoffice.git4jira.app.EMPTY

enum class ProjectProfileType(val value: String) {
    GIT("GitHub"),
    JIRA("Jira"),
    CONVERSATION("Slack"),
    CONTINUOUS_INTEGRATION("TeamCity");

    fun isGitHub() = equals(GIT)
    fun isJira() = equals(JIRA)
    fun isSlack() = equals(CONVERSATION)
    fun isTeamCity() = equals(CONTINUOUS_INTEGRATION)
}

data class ProjectProfileData(var username: String, var projects: MutableList<Project>)

data class Project(
        var name: String,
        var logo: String,
        var credentials: List<Credentials>)

data class Credentials(
        var type: String,
        var username: String = EMPTY,
        var password: String = EMPTY
)

