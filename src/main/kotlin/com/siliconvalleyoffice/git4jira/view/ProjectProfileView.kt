package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.PROFILE_PROFILE_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.PROFILE_PROFILE_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE_VIEW
import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import javafx.scene.control.ListView
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import tornadofx.*
import javax.inject.Inject

class ProjectProfileView : View(), ProjectProfile.View {

    @Inject
    lateinit var projectProfileController: ProjectProfile.Controller

    override val root: BorderPane by fxml(PROJECT_PROFILE_VIEW)

    val addProjectButton: ImageView by fxid("addProject")
    val deleteProjectButton: ImageView by fxid("deleteProject")
    val projectListView: ListView<String> by fxid("listView")
    val tabPane: TabPane by fxid("tabPane")
    val gitTab: Tab by fxid("gitTab")
    val jiraTab: Tab by fxid("jiraTab")
    val slackTab: Tab by fxid("slackTab")
    val teamCityTab: Tab by fxid("teamCityTab")

    init {
        Injector.Instance.appComponent.plus(ProjectProfileModule(this)).inject(this)
        title = "Project Profile"

        assignButtonListeners()
        setPrimaryStageDimensions()
    }

    private fun assignButtonListeners() {
        addProjectButton.setOnMouseClicked { projectProfileController.onAddProjectClick() }
        deleteProjectButton.setOnMouseClicked { projectProfileController.onDeleteProjectClick() }
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = PROFILE_PROFILE_VIEW_WIDTH
        primaryStage.minHeight = PROFILE_PROFILE_VIEW_HEIGHT
//        primaryStage.maxWidth = HOME_VIEW_WIDTH
//        primaryStage.maxHeight = HOME_VIEW_HEIGHT
    }

}