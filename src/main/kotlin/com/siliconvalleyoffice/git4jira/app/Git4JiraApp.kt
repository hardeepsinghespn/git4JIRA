package com.siliconvalleyoffice.git4jira.app

import javafx.stage.Stage
import com.siliconvalleyoffice.git4jira.view.LoginView
import tornadofx.*


class Git4JiraApp: App(LoginView::class, Styles::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        super.start(stage)
        stage.setResizable(true)
        stage.width = LOGIN_VIEW_WIDTH
        stage.height = LOGIN_VIEW_HEIGHT
    }
}