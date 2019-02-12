package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contracts.Home
import com.siliconvalleyoffice.git4jira.view.HomeView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

class HomeController(homeView: HomeView): Home.Controller {

    override fun onEditButtonClick() {
        showMessageDialog("Edit Profile")
    }

    override fun onPrintButtonClick() {
        showMessageDialog("Print Tab Summary")
    }

    override fun onLogoutButtonClick() {
        showMessageDialog("Exit Window")
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