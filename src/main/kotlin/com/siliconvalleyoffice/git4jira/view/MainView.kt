package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.APP_NAME
import com.siliconvalleyoffice.git4jira.app.Styles
import tornadofx.*

class RepositoryListView : View("Repository List") {
    override val root = hbox {
        label("Some Label") {
            addClass(Styles.heading)
        }
    }