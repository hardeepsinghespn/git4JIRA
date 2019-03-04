package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import javafx.stage.StageStyle

class ProjectProfileController(
        private val projectProfileView: ProjectProfile.View,
        private val jsonFilesService: Service.JsonFiles
) : ProjectProfile.Controller {

    override fun projectNames() = jsonFilesService.projectNames()

    override fun onAddProjectClick() {
        CreateProjectView().openModal(StageStyle.DECORATED, resizable = false, block = true)
        projectProfileView.updateListView()
    }

    override fun onDeleteProjectClick() {
        jsonFilesService.removeProject(projectProfileView.listViewSelection())
        projectProfileView.updateListView()
    }

    override fun onListSelectionChanged(selectedValue: String) {
        projectProfileView.defineTabs(jsonFilesService.userConfig.project.firstOrNull { it.name == selectedValue })
    }
}