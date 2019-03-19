package com.siliconvalleyoffice.git4jira.model

import com.siliconvalleyoffice.git4jira.util.GITHUB_PUBLIC_BASE_URL

/**
 * Git GitBaseUrl Model for dynamic GitBaseUrl changes in GitRetrofit
 */
data class GitBaseUrl(var url: String? = GITHUB_PUBLIC_BASE_URL)