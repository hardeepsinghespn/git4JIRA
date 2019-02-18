package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.app.ENCRYPTION_ERROR
import com.siliconvalleyoffice.git4jira.contracts.Git4JiraCredentials
import com.siliconvalleyoffice.git4jira.contracts.Service
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

class Git4JiraCredentialsController(

        private val git4JiraCredentialsView: Git4JiraCredentials.View,
        private val jsonFilesService: Service.JsonFiles

) : Git4JiraCredentials.Controller {

    override fun onValidateClick(): Boolean {
        val result = jsonFilesService.validateCredentials(git4JiraCredentialsView.encryptionPhrase(), git4JiraCredentialsView.encryptionKey())
        if (!result) showMessageDialog(ENCRYPTION_ERROR)
        return result
    }

    override fun validateEncryptionKey(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}