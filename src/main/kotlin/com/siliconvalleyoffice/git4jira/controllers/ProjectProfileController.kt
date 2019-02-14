package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.models.Credentials
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.StageStyle

class ProjectProfileController(val projectProfileView: ProjectProfileView, val jsonFilesService: Service.JsonFiles): ProjectProfile.Controller {

    override fun getProjectNames() = jsonFilesService.getProjectProfileData().projects.map { it.name }

    override fun onAddProjectClick() {
        CreateProjectView().openModal(StageStyle.DECORATED, resizable = false)
    }

    override fun onDeleteProjectClick() {
       showMessageDialog("Delete Project Dialog")
    }

    override fun onListSelectionChanged(selectedValue: String) {
        projectProfileView.defineTabs(getCredentials(selectedValue))
    }

    private fun getCredentials(projectName: String) = jsonFilesService.getProjectProfileData().projects.filter { it.name == projectName }.firstOrNull()?.credentials

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}