package com.siliconvalleyoffice.git4jira.model

import com.siliconvalleyoffice.git4jira.constant.EMPTY

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

