package com.siliconvalleyoffice.git4jira.app

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.view.LoginView
import com.siliconvalleyoffice.git4jira.view.LoginViewOld
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.*

class Git4JiraApp: App(LoginViewOld::class, Styles::class) {

    init {
        Injector.Instance.initialize()
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        super.start(stage)

        //Setup Stage
        FX.primaryStage.icons += Image("https://avatars2.githubusercontent.com/u/10121039?v=4")
        stage.isResizable = true
        stage.width = LOGIN_VIEW_WIDTH
        stage.height = LOGIN_VIEW_HEIGHT
    }
}