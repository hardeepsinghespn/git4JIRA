package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.AVATAR_SIZE
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.text.Text
import loginapp.controllers.LoginController
import tornadofx.*

class UserView : View() {
    override val root = BorderPane()
    var image = Image(LoginController.instance.user.avatarUrl.getValue())
    var avatarView = ImageView(image)
    var nameView = Text(LoginController.instance.user.name.getValue())

    init {
        avatarView.setPreserveRatio(true)
        avatarView.setSmooth(true)
        avatarView.setFitHeight(AVATAR_SIZE)
        root.right = avatarView
        root.center = nameView
    }
}