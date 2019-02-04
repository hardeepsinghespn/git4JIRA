package com.siliconvalleyoffice.git4jira.app

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import com.siliconvalleyoffice.git4jira.view.LoginViewOld
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.*

class Git4JiraApp: App(HomeView::class, Styles::class) {

    init {
        Injector.Instance.initialize()
        reloadStylesheetsOnFocus()
    }
}