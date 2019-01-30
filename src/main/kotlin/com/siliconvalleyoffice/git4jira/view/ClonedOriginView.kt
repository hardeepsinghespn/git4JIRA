package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.AVATAR_SIZE
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import com.siliconvalleyoffice.git4jira.controller.LoginControllerOld
import tornadofx.*

class ClonedOriginView : View() {
    override val root = BorderPane()
    var image = Image(LoginControllerOld.INSTANCE.user.avatarUrl.getValue())
    var avatarView = ImageView(image)
    val selectedClonedOrigin = SimpleStringProperty()
    val theForm = form {
        fieldset(labelPosition = Orientation.HORIZONTAL) {
            field("Cloned Origin") {
                combobox(selectedClonedOrigin, LoginControllerOld.INSTANCE.gitHubController.getForkListMock())
            }
        }
    }

    init {
        root.maxHeight(60.0)
        avatarView.setPreserveRatio(true)
        avatarView.setSmooth(true)
        avatarView.setFitHeight(AVATAR_SIZE)
        root.right = avatarView
        root.center = theForm
//        root.center.setStyle("-fx-background-color: gray");
    }
}