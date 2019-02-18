package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.app.*
import com.siliconvalleyoffice.git4jira.contracts.CreateProject
import com.siliconvalleyoffice.git4jira.contracts.Git4JiraCredentials
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.models.Credentials
import com.siliconvalleyoffice.git4jira.models.Project
import com.siliconvalleyoffice.git4jira.services.RxService
import io.reactivex.subjects.PublishSubject
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.FileChooser
import tornadofx.*

class Git4JiraCredentialsController(

        private val git4JiraCredentialsView: Git4JiraCredentials.View,
        private val jsonFilesService: Service.JsonFiles

) : Git4JiraCredentials.Controller {

    override fun onValidateClick(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateEncryptionKey(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}