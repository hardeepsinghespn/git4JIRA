package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contracts.GitHub
import com.siliconvalleyoffice.git4jira.controller.GitHubController
import com.siliconvalleyoffice.git4jira.view.GitHubView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * GitHub Sub-component to provide Github dependency module
 */
@Subcomponent(modules = [GitHubModule::class])
interface GitHubComponent {
    fun inject(gitHubView: GitHubView)
}

/**
 * GitHub Module to provide GitHub dependencies
 */
@Module
class GitHubModule(private val gitHubView: GitHubView) {

    @Provides
    fun providesGitHubController(): GitHub.Controller = GitHubController(gitHubView)
}