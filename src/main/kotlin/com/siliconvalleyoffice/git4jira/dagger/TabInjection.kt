package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.controller.GitTabController
import com.siliconvalleyoffice.git4jira.model.GitBaseUrl
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.GitTabView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Tabs Sub-component to provide Tabs {GIT, JIRA, COMMUNICATIONS, CONTINUOUS INTEGRATION} dependency module
 */
@Subcomponent(modules = [GitTabModule::class])
interface TabSubComponent {
    fun inject(gitTabView: GitTabView)
}

/**
 * GitTab Module to provide GitTab dependencies
 */
@Module
class GitTabModule(private val gitTabView: GitTab.View) {

    @Provides
    fun providesGitTabController(jsonFilesService: Service.JsonFiles, gitBaseUrl: GitBaseUrl): GitTab.Controller =
            GitTabController(gitTabView, jsonFilesService, gitBaseUrl)
}