package com.siliconvalleyoffice.git4jira.model

/**
 * GitHub Authorization Response
 */
data class GitAuthorizations(val current_user_url: String)

/**
 * GitHub GitHubUserResponse Response
 */
data class GitHubUserResponse(
        val id: Int,
        val login: String,
        val name: String,
        val node_id: String,
        val avatar_url: String,
        val blog: String,
        val email: String,
        val html_url: String,
        val repos_url: String,
        val type: String,
        val url: String
)