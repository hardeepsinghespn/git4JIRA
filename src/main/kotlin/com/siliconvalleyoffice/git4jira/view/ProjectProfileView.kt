package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.constant.PROJECT_PROFILE_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.constant.PROJECT_PROFILE_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.ProjectProfileModule
import com.siliconvalleyoffice.git4jira.model.Credentials
import com.siliconvalleyoffice.git4jira.service.CommunicationEnum
import com.siliconvalleyoffice.git4jira.service.ContinuousIntegrationEnum
import com.siliconvalleyoffice.git4jira.service.GitServiceEnum
import com.siliconvalleyoffice.git4jira.service.ProjectManagementEnum
import com.siliconvalleyoffice.git4jira.service.rx.RxService
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

    val addProjectButton: ImageView by fxid("addProject")
    val deleteProjectButton: ImageView by fxid("deleteProject")
    val projectListView: ListView<String> by fxid("listView")
    val tabPane: TabPane by fxid("tabPane")
    val gitTab: Tab by fxid("gitTab")
    val jiraTab: Tab by fxid("jiraTab")
    val discussionsTab: Tab by fxid("discussionsTab")
    val continuousIntegrationTab: Tab by fxid("continuousIntegrationTab")

    init {
        Injector.Instance.appComponent.plus(ProjectProfileModule(this)).inject(this)
        title = "Project Service"

        setUpInitialView()
        assignListeners()
        setPrimaryStageDimensions()
    }

    private fun setUpInitialView() {
        if (checkIfDataEmpty()) return

        projectListView.items = FXCollections.observableArrayList(projectProfileController.getProjectNames())
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

        projectListView.items = FXCollections.observableArrayList(projectProfileController.getProjectNames())
        projectListView.selectionModel.selectLast()
        projectProfileController.onListSelectionChanged(projectListView.selectionModel.selectedItem)
    }

    override fun defineTabs(credentials: List<Credentials>?) {
        tabPane.tabs.clear()
        credentials?.forEach {
            when (it.type) {
                GitServiceEnum.GITHUB.name -> tabPane.tabs.add(gitTab)
                ProjectManagementEnum.JIRA.name -> tabPane.tabs.add(jiraTab)
                CommunicationEnum.SLACK.name -> tabPane.tabs.add(discussionsTab)
                ContinuousIntegrationEnum.TEAM_CITY.name -> tabPane.tabs.add(continuousIntegrationTab)
            }
        }
    }

    private fun checkIfDataEmpty(): Boolean {
        if (projectProfileController.getProjectNames().isEmpty()) {
            defineTabs(null)
            return true
        }
        return false
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = PROJECT_PROFILE_VIEW_WIDTH
        primaryStage.minHeight = PROJECT_PROFILE_VIEW_HEIGHT
    }
}