package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.constant.PROJECT_PROFILE_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.constant.PROJECT_PROFILE_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.util.PROJECT_PROFILE_VIEW
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

    override val root: BorderPane by fxml(PROJECT_PROFILE_VIEW)

    private val addProjectButton: ImageView by fxid("addProject")
    private val deleteProjectButton: ImageView by fxid("deleteProject")
    private val projectListView: ListView<String> by fxid("listView")
    private val tabPane: TabPane by fxid("tabPane")
    private val gitTab: Tab by fxid("gitTab")
    private val jiraTab: Tab by fxid("jiraTab")
    private val discussionsTab: Tab by fxid("discussionsTab")
    private val continuousIntegrationTab: Tab by fxid("continuousIntegrationTab")

    init {
        Injector.Instance.appComponent.plus(ProjectProfileModule(this)).inject(this)
        title = "Project Service"

        setUpInitialView()
        assignListeners()
        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        if (checkIfDataEmpty()) return

        projectListView.items = FXCollections.observableArrayList(projectProfileController.projectNames())
        projectListView.selectionModel.selectFirst()
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    private fun assignListeners() {
        addProjectButton.setOnMouseClicked { projectProfileController.onAddProjectClick() }
        deleteProjectButton.setOnMouseClicked { projectProfileController.onDeleteProjectClick() }
        projectListView.selectionModel.selectedItemProperty().addListener { _, _, newValue -> if (newValue != null) projectProfileController.onListSelectionChanged(newValue) }
    }

    override fun listViewSelection() = projectListView.selectionModel.selectedItem ?: ""

    override fun updateListView() {
        if (checkIfDataEmpty()) return

        projectListView.items = FXCollections.observableArrayList(projectProfileController.projectNames())
        projectListView.selectionModel.selectLast()
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    override fun defineTabs(project: Project?) {
        tabPane.tabs.clear()

        if (project?.gitService != null) {
            val gitTabView = GitTabView(project.name)
            gitTab.content = gitTabView.root
            tabPane.tabs.add(gitTab)
        }
        if (project?.projectManagementService != null) tabPane.tabs.add(jiraTab)
        if (project?.communicationService != null) tabPane.tabs.add(discussionsTab)
        if (project?.continuousIntegrationService != null) tabPane.tabs.add(continuousIntegrationTab)
    }

    private fun checkIfDataEmpty(): Boolean {
        if (projectProfileController.projectNames().isEmpty()) {
            defineTabs(null)
            projectListView.items.clear()
            return true
        }
        return false
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = PROJECT_PROFILE_VIEW_WIDTH
        primaryStage.minHeight = PROJECT_PROFILE_VIEW_HEIGHT
    }
}