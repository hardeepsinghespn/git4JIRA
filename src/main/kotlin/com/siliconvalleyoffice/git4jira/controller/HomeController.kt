package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.Home
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.ProjectConfigurationView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

class HomeController(val homeView: Home.View,
                     val jsonFileService: Service.JsonFiles) : Home.Controller {

    override fun projectNames() = jsonFileService.projectNames()

    override fun lastSelectedProject() = jsonFileService.getLastSelectedProject()

    override fun onEditButtonClick() {
        ProjectConfigurationView().openWindow(escapeClosesWindow = false, block = true)
        homeView.updateView()
    }

    override fun onPrintButtonClick() {
        showMessageDialog("Print Tab Summary")
    }

    override fun onLogoutButtonClick() {
        homeView.launchLoginView()
    }

    override fun onGitHubErrorClick() {
        ProjectConfigurationView().openModal(escapeClosesWindow = false, block = true)
        homeView.updateView()
    }

    override fun onJiraErrorClick() {
        showMessageDialog("Jira CustomError")
    }

    override fun onSlackErrorClick() {
        showMessageDialog("Slack CustomError")
    }

    override fun onTeamCityClick() {
        showMessageDialog("Team City CustomError")
    }

    override fun onChoiceBoxSelectionChanged(selectedValue: String) {
        jsonFileService.updateLastSelectedProject(selectedValue)
        val lastSelectedProject = jsonFileService.getLastSelectedProject()
        validateCredentials(lastSelectedProject)

        homeView.updateServiceIcons(lastSelectedProject)
        homeView.refreshTabs()
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }

    /**
     * Gather Data and Validate Credentials
     */
    private fun validateCredentials(project: Project?) {
        project?.gitServiceConfig?.gitService()?.authenticate()?.subscribe(
                { homeView.updateGitIcon(true) },
                { homeView.updateGitIcon(false) }
        )

        project?.projectManagementServiceConfig?.jiraService()?.authenticate()?.subscribe(
                { homeView.updateJiraIcon(true) },
                { homeView.updateJiraIcon(false) }
        )
    }
}