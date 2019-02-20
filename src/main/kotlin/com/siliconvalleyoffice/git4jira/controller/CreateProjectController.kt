package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.contract.CreateProject
import com.siliconvalleyoffice.git4jira.model.Credentials
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.rx.RxService
import io.reactivex.subjects.PublishSubject
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
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
        if (isProjectInfoValid()) {
            val credentialsList = generateCredentialsList()
            val project = Project(createProjectView.projectName(), createProjectView.projectLogo(), credentialsList)
            jsonFilesService.addProject(project)
            projectProfileSubject.onNext(RxService.ProjectProfileAction.UPDATE_LIST)
            createProjectView.closeView()
        }
    }

    override fun onCancelClick() = createProjectView.closeView()

    private fun isProjectInfoValid(): Boolean {
        if (createProjectView.projectName().isBlank()) {
            showMessageDialog(MUST_PROVIDE_PROJECT_NAME)
            return false
        }

        if (createProjectView.projectLogo().isBlank()) {
            showMessageDialog(MUST_PROVIDE_PROJECT_LOGO)
            return false
        }

        if(createProjectView.versionControlSelection().isBlank()) {
            showMessageDialog(MUST_PROVIDE_VC)
            return false
        }

        if(createProjectView.projectManagementSelection().isBlank()) {
            showMessageDialog(MUST_PROVIDE_PM)
            return false
        }

        if(createProjectView.communicationSelection().isBlank()) {
            showMessageDialog(MUST_PROVIDE_COMMUNICATION)
            return false
        }

        if(createProjectView.continuousIntegrationSelection().isBlank()) {
            showMessageDialog(MUST_PROVIDE_CI)
            return false
        }

        if(jsonFilesService.projectProfileData.projects.any { it.name.equals(createProjectView.projectName(), true) }) {
            showMessageDialog(PROJECT_ALREADY_EXISTS)
            return false
        }

        return true
    }

    private fun generateCredentialsList(): MutableList<Credentials> {
        val credentialsList = mutableListOf<Credentials>()
        val versionControlSelection = createProjectView.versionControlSelection()
        val projectManagementSelection = createProjectView.projectManagementSelection()
        val communicationSelection = createProjectView.communicationSelection()
        val continuousIntegrationSelection = createProjectView.continuousIntegrationSelection()

        credentialsList.add(Credentials(versionControlSelection))
        credentialsList.add(Credentials(projectManagementSelection))
        if (communicationSelection != NONE) credentialsList.add(Credentials(communicationSelection))
        if (continuousIntegrationSelection != NONE) credentialsList.add(Credentials(continuousIntegrationSelection))

        return credentialsList
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}