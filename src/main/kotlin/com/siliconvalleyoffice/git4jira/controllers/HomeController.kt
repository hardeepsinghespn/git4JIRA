package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.contracts.Home
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

class HomeController(val homeView: HomeView, val loginService: Service.Login): Home.Controller {

    override fun onEditButtonClick() {
        ProjectProfileView().openWindow(escapeClosesWindow = false)
    }

    override fun onPrintButtonClick() {
        showMessageDialog("Print Tab Summary")
    }

    override fun onLogoutButtonClick() {
        //Todo: Need to Fix Resizing
        loginService.logout()
        homeView.replaceWith(LoginView::class, sizeToScene = true, centerOnScreen = true)
    }

    override fun onGitHubErrorClick() {
        showMessageDialog("GitHub Error")
    }

    override fun onJiraErrorClick() {
        showMessageDialog("Jira Error")
    }

    override fun onSlackErrorClick() {
        showMessageDialog("Slack Error")
    }

    override fun onTeamCityClick() {
        showMessageDialog("Team City Error")
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}