package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contract.ProjectConfiguration
import com.siliconvalleyoffice.git4jira.controller.ProjectProfileController
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.ProjectConfigurationView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Home Sub-component to provide ProjectConfiguration dependency module
 */
@Subcomponent(modules = [ProjectProfileModule::class])
interface ProjectProfileSubComponent {
    fun inject(projectConfigurationView: ProjectConfigurationView)
}

/**
 * ProjectConfiguration Module to provide ProjectConfiguration dependencies
 */
@Module
class ProjectProfileModule(private val projectConfigurationView: ProjectConfiguration.View) {

    @Provides
    fun provideProjectProfileController(jsonFilesService: Service.JsonFiles): ProjectConfiguration.Controller
            = ProjectProfileController(projectConfigurationView, jsonFilesService )
}