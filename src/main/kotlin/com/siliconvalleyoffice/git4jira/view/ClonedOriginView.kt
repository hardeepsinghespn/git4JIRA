package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.AVATAR_SIZE
import com.siliconvalleyoffice.git4jira.controller.GitHubController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import com.siliconvalleyoffice.git4jira.controller.LoginController
import tornadofx.*
import java.awt.Color

class ClonedOriginView : View() {
    override val root = BorderPane()
    var image = Image(LoginController.instance.user.avatarUrl.getValue())
    var avatarView = ImageView(image)
    val selectedClonedOrigin = SimpleStringProperty()
    val theForm = form {
        fieldset(labelPosition = Orientation.HORIZONTAL) {
            field("Cloned Origins") {
                combobox(selectedClonedOrigin, GitHubController.instance.originForks)  // getDeveloperForks()
            }
        }
    }

    init {
        avatarView.setPreserveRatio(true)
        avatarView.setSmooth(true)
        avatarView.setFitHeight(AVATAR_SIZE)
        root.right = avatarView
        root.center = theForm
//        root.center.setStyle("-fx-background-color: gray");
    }
}