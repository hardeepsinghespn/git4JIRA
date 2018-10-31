package com.siliconvalleyoffice.git4jira.app

import com.siliconvalleyoffice.git4jira.view.RepositoryListView
import com.sun.org.apache.bcel.internal.classfile.Constant
import javafx.stage.Stage
import tornadofx.App

class Git4JiraApp: App(RepositoryListView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.minWidth  = WINDOW_MIN_WIDTH
        stage.minHeight = WINDOW_MIN_HEIGHT
    }
}