package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.contract.ProjectConfiguration
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.style.sceneScalingFactor
import com.siliconvalleyoffice.git4jira.util.*
import com.siliconvalleyoffice.git4jira.view.tab.GitTabView
import com.siliconvalleyoffice.git4jira.view.tab.JiraTabView
import javafx.collections.FXCollections
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.transform.Scale
import tornadofx.*
import javax.inject.Inject


class ProjectConfigurationView: View(), ProjectConfiguration.View {

    @Inject
    lateinit var projectConfigurationController: ProjectConfiguration.Controller

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
        title = "Project Configuration"

        setUpInitialView()
        assignAccelerators()
        assignListeners()
//        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        if (checkIfDataEmpty()) return

        projectListView.items = FXCollections.observableArrayList(projectConfigurationController.projectNames())
        projectListView.selectionModel.select(projectConfigurationController.lastSelectedProjectName())
        projectConfigurationController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    private fun assignAccelerators() {
        projectListView.setCellFactory {
            val cell = ListCell<String>()
            val contextMenu = ContextMenu()
            val editItem = MenuItem(EDIT)
            editItem.setOnAction { projectConfigurationController.onEditProjectClick(cell.item) }

            val deleteItem = MenuItem(DELETE)
            deleteItem.setOnAction { projectConfigurationController.onDeleteProjectClick(cell.item) }
            contextMenu.items.addAll(editItem, deleteItem)

            cell.textProperty().bind(cell.itemProperty())
            if (!cell.isEmpty) cell.contextMenu = contextMenu
            cell.emptyProperty().addListener { _, _, isNowEmpty -> if (!isNowEmpty) cell.contextMenu = contextMenu }
            cell
        }
    }

    private fun assignListeners() {
        addProjectButton.setOnMouseClicked { projectConfigurationController.onAddProjectClick() }
        deleteProjectButton.setOnMouseClicked { projectConfigurationController.onDeleteProjectClick(projectListView.selectedItem ?: EMPTY) }
        projectListView.selectionModel.selectedItemProperty().addListener { _, _, newValue -> if (newValue != null) projectConfigurationController.onListSelectionChanged(newValue) }
    }

    override fun listViewSelection() = projectListView.selectionModel.selectedItem ?: ""

    override fun updateListView() {
        if (checkIfDataEmpty()) return

        projectListView.items = FXCollections.observableArrayList(projectConfigurationController.projectNames())
        projectListView.selectionModel.selectLast()
        projectConfigurationController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    override fun defineTabs(project: Project?) {
        tabPane.tabs.clear()

        if (project?.gitServiceConfig != null) {
            gitTab.content = GitTabView(this, project.name).root
            tabPane.tabs.add(gitTab)
        }
        if (project?.projectManagementServiceConfig != null) {
            jiraTab.content = JiraTabView(this, project.name).root
            tabPane.tabs.add(jiraTab)
        }
        if (project?.communicationServiceConfig != null) tabPane.tabs.add(communicationTab)
        if (project?.continuousIntegrationServiceConfig != null) tabPane.tabs.add(continuousIntegrationTab)
    }

    private fun checkIfDataEmpty(): Boolean {
        if (projectConfigurationController.projectNames().isEmpty()) {
            defineTabs(null)
            projectListView.items.clear()
            return true
        }
        return false
    }

    private fun setPrimaryStageDimensions() {
        root.prefWidth = PROJECT_CONFIGURATION_VIEW_WIDTH
        root.prefHeight = PROJECT_CONFIGURATION_VIEW_HEIGHT
        val scale = Scale(sceneScalingFactor, sceneScalingFactor)
        scale.pivotX = 0.0
        scale.pivotY = 0.0
        root.transforms.setAll(scale)
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