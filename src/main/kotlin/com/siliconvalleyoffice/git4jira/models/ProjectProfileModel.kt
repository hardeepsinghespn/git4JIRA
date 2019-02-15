package com.siliconvalleyoffice.git4jira.models

import com.siliconvalleyoffice.git4jira.app.EMPTY

enum class ProjectProfileType(val value: String) {
    GITHUB("GitHub"),
    JIRA("Jira"),
    SLACK("Slack"),
    TEAM_CITY("TeamCity");

    fun isGitHub() = equals(GITHUB)
    fun isJira() = equals(JIRA)
    fun isSlack() = equals(SLACK)
    fun isTeamCity() = equals(TEAM_CITY)
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

