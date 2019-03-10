package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contract.Git4JiraCredentials
import com.siliconvalleyoffice.git4jira.controller.Git4JiraCredentialsController
import com.siliconvalleyoffice.git4jira.service.Service
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import view.Git4JiraCredentialsView

/**
 * Create Project Sub-component to provide Create Project dependency module
 */
@Subcomponent(modules = [Git4JiraCredentialsModule::class])
interface Git4JiraCredentialsSubComponent {
    fun inject(git4JiraCredentialsView: Git4JiraCredentialsView)
}

/**
 * Create Project Module to provide Create Project dependencies
 */
@Module
class Git4JiraCredentialsModule(private val git4JiraCredentialsView: Git4JiraCredentials.View) {

    @Provides
    fun providesGit4JiraCredentialsController(jsonFilesService: Service.JsonFiles): Git4JiraCredentials.Controller
            = Git4JiraCredentialsController(git4JiraCredentialsView, jsonFilesService)
}