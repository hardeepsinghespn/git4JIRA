package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE_VIEW
import com.siliconvalleyoffice.git4jira.contracts.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import com.siliconvalleyoffice.git4jira.models.Credentials
import com.siliconvalleyoffice.git4jira.models.ProjectProfileType
import com.siliconvalleyoffice.git4jira.services.RxService
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
    lateinit var rxService: RxService

    override val root: BorderPane by fxml(PROJECT_PROFILE_VIEW)

    val addProjectButton: ImageView by fxid("addProject")
    val deleteProjectButton: ImageView by fxid("deleteProject")
    val projectListView: ListView<String> by fxid("listView")
    val tabPane: TabPane by fxid("tabPane")
    val gitTab: Tab by fxid("gitTab")
    val jiraTab: Tab by fxid("jiraTab")
    val conversationTab: Tab by fxid("conversationTab")
    val continuousIntegrationTab: Tab by fxid("continuousIntegrationTab")

    init {
        Injector.Instance.appComponent.plus(ProjectProfileModule(this)).inject(this)
        title = "Project Profile"

        setUpInitialView()
        assignListeners()
        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        projectListView.items = FXCollections.observableArrayList(projectProfileController.getProjectNames())
        projectListView.selectionModel.selectFirst()
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    private fun assignListeners() {
        addProjectButton.setOnMouseClicked { projectProfileController.onAddProjectClick() }
        deleteProjectButton.setOnMouseClicked { projectProfileController.onDeleteProjectClick() }
        projectListView.selectionModel.selectedItemProperty().addListener { _, _, newValue -> if(newValue != null) projectProfileController.onListSelectionChanged(newValue) }
    }

    override fun listViewSelection() = projectListView.selectionModel.selectedItem ?: ""

    override fun updateListView() {
        projectListView.items = FXCollections.observableArrayList(projectProfileController.getProjectNames())
        projectListView.selectionModel.selectLast()
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    override fun defineTabs(credentials: List<Credentials>?) {
        tabPane.tabs.clear()
        credentials?.forEach {
            when (it.type) {
                ProjectProfileType.GIT.value -> tabPane.tabs.add(gitTab)
                ProjectProfileType.JIRA.value -> tabPane.tabs.add(jiraTab)
                ProjectProfileType.CONVERSATION.value -> tabPane.tabs.add(conversationTab)
                ProjectProfileType.CONTINUOUS_INTEGRATION.value -> tabPane.tabs.add(continuousIntegrationTab)
            }
        }
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = PROJECT_PROFILE_VIEW_WIDTH
        primaryStage.minHeight = PROJECT_PROFILE_VIEW_HEIGHT
    }
}