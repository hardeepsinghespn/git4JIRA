package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contracts.CreateProject
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.controllers.CreateProjectController
import com.siliconvalleyoffice.git4jira.services.JsonFilesService
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Create Project Sub-component to provide Create Project dependency module
 */
@Subcomponent(modules = [CreateProjectModule::class])
interface CreateProjectSubComponent {
    fun inject(createProjectView: CreateProjectView)
}

/**
 * Create Project Module to provide Create Project dependencies
 */
@Module
class CreateProjectModule(private val createProjectView: CreateProjectView) {

    @Provides
    fun providesCreateProjectController(jsonFilesService: Service.JsonFiles): CreateProject.Controller = CreateProjectController(createProjectView, jsonFilesService)
}