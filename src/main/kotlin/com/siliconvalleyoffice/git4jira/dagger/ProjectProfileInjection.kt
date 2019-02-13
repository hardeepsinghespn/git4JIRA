package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.controller.ProjectProfileController
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Home Sub-component to provide ProjectProfile dependency module
 */
@Subcomponent(modules = [ProjectProfileModule::class])
interface ProjectProfileSubComponent {
    fun inject(projectProfileView: ProjectProfileView)
}

/**
 * ProjectProfile Module to provide ProjectProfile dependencies
 */
@Module
class ProjectProfileModule(private val projectProfileView: ProjectProfileView) {

    @Provides
    fun provideProjectProfileController(): ProjectProfile.Controller = ProjectProfileController(projectProfileView)
}