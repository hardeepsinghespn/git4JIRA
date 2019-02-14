package com.siliconvalleyoffice.git4jira.models

import com.google.gson.annotations.SerializedName

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

data class ProjectProfileData(var username: String, var projects: List<Projects>)

data class Projects(
        var name: String,
        var credentials: List<Credentials>)

data class Credentials(
        var type: String,
        var username: String,
        var password: String
)

