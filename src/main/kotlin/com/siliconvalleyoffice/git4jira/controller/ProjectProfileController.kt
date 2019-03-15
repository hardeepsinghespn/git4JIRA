package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.constant.DELETE_DIALOG_MESSAGE
import com.siliconvalleyoffice.git4jira.constant.DELETE_PROJECT
import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.stage.StageStyle
import tornadofx.*

class ProjectProfileController(
        private val projectProfileView: ProjectProfile.View,
        private val jsonFilesService: Service.JsonFiles
) : ProjectProfile.Controller {

    override fun projectNames() = jsonFilesService.projectNames()

    override fun onAddProjectClick() {
        CreateProjectView().openModal(StageStyle.DECORATED, resizable = false, block = true)
        projectProfileView.updateListView()
    }

    override fun onEditProjectClick(projectName: String) {
        CreateProjectView(projectName).openModal(StageStyle.DECORATED, resizable = false, block = true)
        projectProfileView.updateListView()
    }

    override fun onDeleteProjectClick(projectName: String) {
        if (showDeleteConfirmationDialog()) {
            jsonFilesService.removeProject(projectName)
            projectProfileView.updateListView()
        }
    }

    override fun onListSelectionChanged(selectedValue: String) {
        projectProfileView.defineTabs(jsonFilesService.userConfig.project.firstOrNull { it.name == selectedValue })
    }

    private fun showDeleteConfirmationDialog(): Boolean {
        val alert = confirmation(DELETE_PROJECT, DELETE_DIALOG_MESSAGE, actionFn = { btnType -> return btnType.buttonData == ButtonBar.ButtonData.OK_DONE })
        alert.showAndWait()
        return false
    }
}