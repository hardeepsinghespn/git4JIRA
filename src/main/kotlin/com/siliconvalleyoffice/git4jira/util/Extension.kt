package com.siliconvalleyoffice.git4jira.util


fun String.prepareHttpsUrl() = HTTPS + this + SLASH

fun String.prepareGitApiUrl() = this.removeSuffix(SLASH) + API_V3

fun String.prepareJiraApiUrl() = this.removeSuffix(SLASH) + REST_AGILE_LATEST

