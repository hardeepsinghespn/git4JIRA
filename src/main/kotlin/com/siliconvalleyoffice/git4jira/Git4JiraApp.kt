package com.siliconvalleyoffice.git4jira

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.service.*
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import tornadofx.*
import view.Git4JiraCredentialsView

class Git4JiraApp : App(Git4JiraCredentialsView::class) {

    init {
        Injector.Instance.initialize()
        reloadStylesheetsOnFocus()
    }
}