package com.siliconvalleyoffice.git4jira.app

import com.siliconvalleyoffice.git4jira.view.LoginView
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.*


class Git4JiraApp: App(LoginView::class, Styles::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        super.start(stage)
        FX.primaryStage.icons += Image("https://avatars2.githubusercontent.com/u/10121039?v=4")
//        FX.primaryStage.icons += Image("file:./git4JIRA.png")
//        FX.primaryStage.icons += Image(this::class.java.getResourceAsStream("/com/siliconvalleyoffice/git4jira/asset/git4JIRA.png"))
        stage.setResizable(true)
        stage.width = LOGIN_VIEW_WIDTH
        stage.height = LOGIN_VIEW_HEIGHT
    }
}