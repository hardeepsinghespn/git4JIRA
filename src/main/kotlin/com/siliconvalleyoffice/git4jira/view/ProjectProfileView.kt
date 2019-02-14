package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.PROFILE_PROFILE_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.PROFILE_PROFILE_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE_VIEW
import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import com.siliconvalleyoffice.git4jira.models.Credentials
import com.siliconvalleyoffice.git4jira.models.ProjectProfileType
import javafx.collections.FXCollections
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

    @Inject
    lateinit var jsonFilesService: Service.JsonFiles

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

        setUpInitialView()
        assignListeners()
        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        val projectProfileData = jsonFilesService.getProjectProfileData()
        projectListView.items = FXCollections.observableArrayList(projectProfileData.projects.map { it.name })
        projectListView.selectionModel.selectFirst()
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    private fun assignListeners() {
        addProjectButton.setOnMouseClicked { projectProfileController.onAddProjectClick() }
        deleteProjectButton.setOnMouseClicked { projectProfileController.onDeleteProjectClick() }
        projectListView.selectionModel.selectedItemProperty().addListener { _, _, newValue -> projectProfileController.onListSelectionChanged(newValue) }
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = PROFILE_PROFILE_VIEW_WIDTH
        primaryStage.minHeight = PROFILE_PROFILE_VIEW_HEIGHT
//        primaryStage.maxWidth = HOME_VIEW_WIDTH
//        primaryStage.maxHeight = HOME_VIEW_HEIGHT
    }

    override fun defineTabs(credentials: List<Credentials>?) {
        tabPane.tabs.clear()
        credentials?.forEach {
            when (it.type) {
                ProjectProfileType.GITHUB.value -> tabPane.tabs.add(gitTab)
                ProjectProfileType.JIRA.value -> tabPane.tabs.add(jiraTab)
                ProjectProfileType.SLACK.value -> tabPane.tabs.add(slackTab)
                ProjectProfileType.TEAM_CITY.value -> tabPane.tabs.add(teamCityTab)
            }
        }
    }

}