package com.siliconvalleyoffice.git4jira.util

/**
 * Prepare a HTTPS url with appropriate slashes
 */
fun String.prepareHttpsUrl() = HTTPS + this + SLASH
