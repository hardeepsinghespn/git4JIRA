package com.siliconvalleyoffice.git4jira.controllers

import com.siliconvalleyoffice.git4jira.app.JSON_EXTENSIONS
import com.siliconvalleyoffice.git4jira.contracts.CreateProject
import com.siliconvalleyoffice.git4jira.view.CreateProjectView
import javafx.stage.FileChooser
import tornadofx.*

class CreateProjectController(private val createProjectView: CreateProjectView): CreateProject.Controller {

    override fun onBrowseClick() {
        chooseFile(title = "Select Project Logo", filters = arrayOf(FileChooser.ExtensionFilter(JSON_EXTENSIONS)))
    }

    override fun onCreateClick() {
        //Todo
    }

    override fun onCancelClick() {
        createProjectView.close()
    }
}