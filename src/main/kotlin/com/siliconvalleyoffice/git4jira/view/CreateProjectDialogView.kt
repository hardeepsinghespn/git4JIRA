package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.CREATE_PROJECT_DIALOG_VIEW
import javafx.scene.layout.BorderPane
import tornadofx.*

class CreateProjectDialogView: View() {

    override val root: BorderPane by fxml(CREATE_PROJECT_DIALOG_VIEW)
}