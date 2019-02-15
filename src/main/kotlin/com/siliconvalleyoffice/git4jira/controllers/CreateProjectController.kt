package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.app.JSON_EXTENSIONS
import com.siliconvalleyoffice.git4jira.app.JSON_EXTENSION_DESCRIPTION
import com.siliconvalleyoffice.git4jira.app.SELECT_PROJECT_LOGO
import com.siliconvalleyoffice.git4jira.contracts.CreateProject
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.models.Credentials
import com.siliconvalleyoffice.git4jira.models.Project
import com.siliconvalleyoffice.git4jira.services.RxService
import io.reactivex.subjects.PublishSubject
import javafx.stage.FileChooser
import tornadofx.*

class CreateProjectController(
        private val createProjectView: CreateProject.View,
        private val jsonFilesService: Service.JsonFiles,
        private val projectProfileSubject: PublishSubject<RxService.ProjectProfileAction>
) : CreateProject.Controller {

    override fun versionControlItems() = jsonFilesService.configuration.projectProfileOptions.versionControl

    override fun projectManagementItems() = jsonFilesService.configuration.projectProfileOptions.projectManagement

    override fun communicationItems() = jsonFilesService.configuration.projectProfileOptions.communication

    override fun continuousIntegrationItems() = jsonFilesService.configuration.projectProfileOptions.continuousIntegration

    override fun onBrowseClick() {
        val filePath = chooseFile(title = SELECT_PROJECT_LOGO, filters = arrayOf(FileChooser.ExtensionFilter(JSON_EXTENSION_DESCRIPTION, JSON_EXTENSIONS))).firstOrNull()
        createProjectView.updateProjectLogoPath(filePath)
    }

    override fun onCreateClick() {
        val credentialsList = generateCredentialsList()
        val project = Project(createProjectView.projectName(), createProjectView.projectLogo(), credentialsList)
        jsonFilesService.addProject(project)
        projectProfileSubject.onNext(RxService.ProjectProfileAction.UPDATE_LIST)
        createProjectView.closeView()
    }

    private fun generateCredentialsList(): MutableList<Credentials> {
        val credentialsList = mutableListOf<Credentials>()
        val versionControlSelection = createProjectView.versionControlSelection()
        val projectManagementSelection = createProjectView.projectManagementSelection()
        val communicationSelection = createProjectView.communicationSelection()
        val continuousIntegrationSelection = createProjectView.continuousIntegrationSelection()

        if (!versionControlSelection.isBlank()) credentialsList.add(Credentials(versionControlSelection))
        if (!projectManagementSelection.isBlank()) credentialsList.add(Credentials(projectManagementSelection))
        if (!communicationSelection.isBlank()) credentialsList.add(Credentials(communicationSelection))
        if (!continuousIntegrationSelection.isBlank()) credentialsList.add(Credentials(continuousIntegrationSelection))

        return credentialsList
    }

    override fun onCancelClick() {
        createProjectView.closeView()
    }
}