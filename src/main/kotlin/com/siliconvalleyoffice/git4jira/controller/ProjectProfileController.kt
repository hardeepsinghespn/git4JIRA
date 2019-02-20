package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.rx.RxService
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import io.reactivex.Observable
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

    private fun subscribeToEvents() = projectProfileSubject.take(1).subscribe { projectProfileView.updateListView() }

    private fun getCredentials(projectName: String) =
            jsonFilesService.projectProfileData.projects.firstOrNull { it.name == projectName }?.credentials
}