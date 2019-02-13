package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

class ProjectProfileController(val projectProfileView: ProjectProfileView): ProjectProfile.Controller {

    override fun onAddProjectClick() {
        showMessageDialog("Add Project Dialog")
    }

    override fun onDeleteProjectClick() {
       showMessageDialog("Delete Project Dialog")
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}