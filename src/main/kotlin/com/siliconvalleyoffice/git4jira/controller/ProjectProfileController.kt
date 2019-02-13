package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.view.CreateProjectDialogView
import com.siliconvalleyoffice.git4jira.view.ProjectProfileView
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.StageStyle

class ProjectProfileController(val projectProfileView: ProjectProfileView): ProjectProfile.Controller {

    override fun onAddProjectClick() {
        CreateProjectDialogView().openModal(StageStyle.DECORATED, resizable = false)
    }

    override fun onDeleteProjectClick() {
       showMessageDialog("Delete Project Dialog")
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}