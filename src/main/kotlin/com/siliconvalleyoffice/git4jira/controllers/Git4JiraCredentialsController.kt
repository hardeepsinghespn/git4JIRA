package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.app.ENCRYPTION_ERROR
import com.siliconvalleyoffice.git4jira.contracts.Git4JiraCredentials
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.view.HomeView
import javafx.application.Platform.runLater
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import view.Git4JiraCredentialsView

class Git4JiraCredentialsController(

        private val git4JiraCredentialsView: Git4JiraCredentialsView,
        private val jsonFilesService: Service.JsonFiles

) : Git4JiraCredentials.Controller {

    override fun onValidateClick() {
        val result = jsonFilesService.validateCredentials(git4JiraCredentialsView.encryptionPhrase(), git4JiraCredentialsView.encryptionKey())
        if (!result) showMessageDialog(ENCRYPTION_ERROR)
        else runLater { git4JiraCredentialsView.replaceWith(HomeView::class, sizeToScene = true, centerOnScreen = true) }
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}