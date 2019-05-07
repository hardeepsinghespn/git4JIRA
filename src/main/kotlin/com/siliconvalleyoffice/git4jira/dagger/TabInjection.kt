package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.contract.JiraTab
import com.siliconvalleyoffice.git4jira.controller.GitTabController
import com.siliconvalleyoffice.git4jira.controller.JiraTabController
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.tab.GitTabView
import com.siliconvalleyoffice.git4jira.view.tab.JiraTabView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Tabs Sub-component to provide Tabs {GIT} dependency module
 */
@Subcomponent(modules = [GitTabModule::class])
interface GitTabSubComponent {
    fun inject(gitTabView: GitTabView)
}

/**
 * GitTab Module to provide GitTab dependencies
 */
@Module
class GitTabModule(private val gitTabView: GitTab.View) {

    @Provides
    fun providesGitTabController(jsonFilesService: Service.JsonFiles): GitTab.Controller =
            GitTabController(gitTabView, jsonFilesService)
}


/**
 * Tabs Sub-component to provide Tabs {JIRA} dependency module
 */
@Subcomponent(modules = [JiraTabModule::class])
interface JiraTabSubComponent {
    fun inject(jiraTabView: JiraTabView)
}

/**
 * JiraTab Module to provide JiraTab dependencies
 */
@Module
class JiraTabModule(private val jiraTabView: JiraTab.View) {

    @Provides
    fun providesJiraTabController(jsonFilesService: Service.JsonFiles): JiraTab.Controller =
            JiraTabController(jiraTabView, jsonFilesService)
}

