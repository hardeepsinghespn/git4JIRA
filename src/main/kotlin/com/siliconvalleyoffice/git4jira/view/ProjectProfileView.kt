package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE_VIEW
import javafx.scene.layout.BorderPane
import tornadofx.*

class ProjectProfileView: View() {

    override val root: BorderPane by fxml(PROJECT_PROFILE_VIEW)

    init {
        title = "Project Profile"
    }
}