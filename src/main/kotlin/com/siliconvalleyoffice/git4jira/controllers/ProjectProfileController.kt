package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.services.RxService
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import io.reactivex.Observable
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.StageStyle

class ProjectProfileController(
        private val projectProfileView: ProjectProfile.View,
        private val jsonFilesService: Service.JsonFiles,
        private val projectProfileSubject: Observable<RxService.ProjectProfileAction>
) : ProjectProfile.Controller {

    override fun getProjectNames() = jsonFilesService.projectProfileData.projects.map { it.name }

    override fun onAddProjectClick() {
        CreateProjectView().openModal(StageStyle.DECORATED, resizable = false)
        subscribeToEvents()
    }

    override fun onDeleteProjectClick() {
        jsonFilesService.removeProject(projectProfileView.listViewSelection())
        projectProfileView.updateListView()
    }

    override fun onListSelectionChanged(selectedValue: String) {
        projectProfileView.defineTabs(getCredentials(selectedValue))
    }

    private fun subscribeToEvents() = projectProfileSubject.subscribe { projectProfileView.updateListView() }

    private fun getCredentials(projectName: String) = jsonFilesService.projectProfileData.projects.firstOrNull { it.name == projectName }?.credentials

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}