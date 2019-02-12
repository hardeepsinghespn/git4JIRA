package com.siliconvalleyoffice.git4jira.app

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import tornadofx.*

class Git4JiraApp : App(LoginView::class) {

    init {
        Injector.Instance.initialize()
        reloadStylesheetsOnFocus()
    }
}