package com.siliconvalleyoffice.git4jira.app

import javafx.stage.Stage
import tornadofx.*
import view.MainView

class Git4JiraApp: App(MainView::class, Styles::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = WINDOW_MIN_WIDTH
        stage.height = WINDOW_MIN_HEIGHT
    }
}