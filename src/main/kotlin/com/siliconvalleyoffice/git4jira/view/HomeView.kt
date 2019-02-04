package com.siliconvalleyoffice.git4jira.view

import javafx.scene.Parent
import javafx.scene.layout.AnchorPane
import tornadofx.*

class HomeView: View("Home View") {

    override val root: AnchorPane by fxml("/fxml/HomeView.fxml")
}