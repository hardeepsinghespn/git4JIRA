package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.constant.DELETE_DIALOG_MESSAGE
import com.siliconvalleyoffice.git4jira.constant.DELETE_PROJECT
import com.siliconvalleyoffice.git4jira.contract.ProjectConfiguration
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import javafx.scene.control.ButtonBar
import javafx.stage.StageStyle
import tornadofx.*

class ProjectProfileController(
        private val projectConfigurationView: ProjectConfiguration.View,
        private val jsonFilesService: Service.JsonFiles
) : ProjectConfiguration.Controller {

    override fun projectNames() = jsonFilesService.projectNames()

    override fun lastSelectedProjectName() = jsonFilesService.getLastSelectedProject()?.name

    override fun onAddProjectClick() {
        CreateProjectView().openModal(StageStyle.DECORATED, resizable = false, block = true)
        projectConfigurationView.updateListView()
    }

    override fun onEditProjectClick(projectName: String) {
        CreateProjectView(projectName).openModal(StageStyle.DECORATED, resizable = false, block = true)
        projectConfigurationView.updateListView()
    }

    override fun onDeleteProjectClick(projectName: String) {
        if (showDeleteConfirmationDialog()) {
            jsonFilesService.removeProject(projectName)
            projectConfigurationView.updateListView()
        }
    }

    override fun onListSelectionChanged(selectedValue: String) {
        projectConfigurationView.defineTabs(jsonFilesService.userConfig.project.firstOrNull { it.name == selectedValue })
    }

    private fun showDeleteConfirmationDialog(): Boolean {
        val alert = confirmation(DELETE_PROJECT, DELETE_DIALOG_MESSAGE, actionFn = { btnType -> return btnType.buttonData == ButtonBar.ButtonData.OK_DONE })
        alert.showAndWait()
        return false
    }
}