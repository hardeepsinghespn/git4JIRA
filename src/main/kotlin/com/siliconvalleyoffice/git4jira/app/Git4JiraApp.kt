package com.siliconvalleyoffice.git4jira.app

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import tornadofx.*
import view.Git4JiraCredentialsView

class Git4JiraApp : App(HomeView::class) {

    init {
        Injector.Instance.initialize()
        reloadStylesheetsOnFocus()
    }
}