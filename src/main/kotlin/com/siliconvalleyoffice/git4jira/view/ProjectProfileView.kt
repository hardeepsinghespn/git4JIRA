package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.util.*
import javafx.collections.FXCollections
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import tornadofx.*
import javax.inject.Inject


class ProjectProfileView: View(), ProjectProfile.View {

    @Inject
    lateinit var projectProfileController: ProjectProfile.Controller

    override val root: BorderPane by fxml(PROJECT_PROFILE_VIEW)

    private val addProjectButton: ImageView by fxid("addProject")
    private val deleteProjectButton: ImageView by fxid("deleteProject")
    private val projectListView: ListView<String> by fxid("listView")
    private val tabPane: TabPane by fxid("tabPane")
    private val gitTab: Tab by fxid("gitTab")
    private val gitTabIcon: ImageView by fxid("gitTabIcon")
    private val jiraTab: Tab by fxid("jiraTab")
    private val jiraTabIcon: ImageView by fxid("jiraTabIcon")
    private val communicationTab: Tab by fxid("communicationTab")
    private val communicationTabIcon: ImageView by fxid("communicationTab")
    private val continuousIntegrationTab: Tab by fxid("continuousIntegrationTab")
    private val continuousIntegrationTabIcon: ImageView by fxid("continuousIntegrationTabIcon")

    init {
        Injector.Instance.appComponent.plus(ProjectProfileModule(this)).inject(this)
        title = "Project Services"

        setUpInitialView()
        assignAccelerators()
        assignListeners()
        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        if (checkIfDataEmpty()) return

        projectListView.items = FXCollections.observableArrayList(projectProfileController.projectNames())
        projectListView.selectionModel.select(projectProfileController.lastSelectedProjectName())
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    private fun assignAccelerators() {
        projectListView.setCellFactory {
            val cell = ListCell<String>()
            val contextMenu = ContextMenu()
            val editItem = MenuItem(EDIT)
            editItem.setOnAction { projectProfileController.onEditProjectClick(cell.item) }

            val deleteItem = MenuItem(DELETE)
            deleteItem.setOnAction { projectProfileController.onDeleteProjectClick(cell.item) }
            contextMenu.items.addAll(editItem, deleteItem)

            cell.textProperty().bind(cell.itemProperty())
            if (!cell.isEmpty) cell.contextMenu = contextMenu
            cell.emptyProperty().addListener { _, _, isNowEmpty -> if (!isNowEmpty) cell.contextMenu = contextMenu }
            cell
        }
    }

    private fun assignListeners() {
        addProjectButton.setOnMouseClicked { projectProfileController.onAddProjectClick() }
        deleteProjectButton.setOnMouseClicked { projectProfileController.onDeleteProjectClick(projectListView.selectedItem ?: EMPTY) }
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

        if (project?.gitServiceConfig != null) {
            gitTab.content = GitTabView(this, project.name).root
            tabPane.tabs.add(gitTab)
        }
        if (project?.projectManagementServiceConfig != null) tabPane.tabs.add(jiraTab)
        if (project?.communicationServiceConfig != null) tabPane.tabs.add(communicationTab)
        if (project?.continuousIntegrationServiceConfig != null) tabPane.tabs.add(continuousIntegrationTab)
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

    override fun updateGitTabIcon(icon: String?) {
        gitTabIcon.image = Image(icon)
    }

    override fun updateJiraTabIcon(icon: String?) {
        jiraTabIcon.image = Image(icon)
    }

    override fun updateCommunicationTabIcon(icon: String?) {
        communicationTabIcon.image = Image(icon)
    }

    override fun updateContinuousIntegrationTabIcon(icon: String?) {
        continuousIntegrationTabIcon.image = Image(icon)
    }
}