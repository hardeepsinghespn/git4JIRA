package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.Home
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.json.JsonFileService
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import view.Git4JiraCredentialsView

class HomeController(val homeView: HomeView,
                     val loginService: Service.Login,
                     val jsonFileService: Service.JsonFiles): Home.Controller {

    override fun projectNames() = jsonFileService.projectNames()

    override fun onEditButtonClick() {
        ProjectProfileView().openWindow(escapeClosesWindow = false)
    }

    override fun onPrintButtonClick() {
        showMessageDialog("Print Tab Summary")
    }

    override fun onLogoutButtonClick() {
        //Todo: Need to Fix Resizing
        loginService.logout()
        homeView.replaceWith(Git4JiraCredentialsView::class, sizeToScene = true, centerOnScreen = true)
    }

    override fun onGitHubErrorClick() {
        showMessageDialog("GitHub CustomError")
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

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}