package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.constant.HOME_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.constant.HOME_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.contract.Home
import com.siliconvalleyoffice.git4jira.dagger.HomeModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.style.sceneScalingFactor
import com.siliconvalleyoffice.git4jira.util.*
import javafx.collections.FXCollections
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Border
import javafx.scene.layout.BorderPane
import tornadofx.*
import view.Git4JiraCredentialsView
import javax.inject.Inject
import javafx.scene.transform.Scale




class HomeView: View(), Home.View {

    @Inject
    lateinit var homeController: Home.Controller

    override val root: AnchorPane by fxml(HOME_VIEW)

    val logoImageView: ImageView by fxid("projectLogoImageView")
    val profileChoiceBox: ChoiceBox<String> by fxid("profilePicker")

    val githubErrorImage: ImageView by fxid("githubErrorImage")
    val jiraErrorImage: ImageView by fxid("jiraErrorImage")
    val slackErrorImage: ImageView by fxid("slackErrorImage")
    val teamCityErrorImage: ImageView by fxid("teamCityErrorImage")

    val editButton: ImageView by fxid("editButton")
    val printButton: ImageView by fxid("printButton")
    val logoutButton: ImageView by fxid("logoutButton")

    val tabPane: TabPane by fxid("tabPane")
    val releaseTab: Tab by fxid("releaseTab")
    val squadTab: Tab by fxid("squadTab")
    val branchTab: Tab by fxid("branchTab")
    val prTab: Tab by fxid("prTab")
    val teamStressTab: Tab by fxid("teamStressTab")

    init {
        Injector.Instance.appComponent.plus(HomeModule(this)).inject(this)
        title = "git4Jira"

        assignAccelerators()
        assignListeners()
        updateView()
        setPrimaryStageDimensions()
    }

    private fun assignListeners() {
        editButton.setOnMouseClicked { homeController.onEditButtonClick() }
        printButton.setOnMouseClicked { homeController.onPrintButtonClick() }
        logoutButton.setOnMouseClicked { homeController.onLogoutButtonClick() }
        githubErrorImage.setOnMouseClicked { homeController.onGitHubErrorClick() }
        jiraErrorImage.setOnMouseClicked { homeController.onJiraErrorClick() }
        slackErrorImage.setOnMouseClicked { homeController.onSlackErrorClick() }
        teamCityErrorImage.setOnMouseClicked { homeController.onTeamCityClick() }

        profileChoiceBox.selectionModel.selectedItemProperty().addListener { _, _, newValue -> if (newValue != null) homeController.onChoiceBoxSelectionChanged(newValue) }
    }

    override fun updateView() {
        val projectNames = homeController.projectNames()
        val lastSelectedProject = homeController.lastSelectedProject()
        profileChoiceBox.selectionModel.select(null)
        updateServiceIcons(lastSelectedProject)
        if(projectNames.isEmpty()) return

        profileChoiceBox.items = FXCollections.observableArrayList(projectNames)
        profileChoiceBox.selectionModel.select(lastSelectedProject?.name)
        //logoImageView.image = Image(lastSelectedProject?.logo)
    }

    override fun refreshTabs() {
        println("Refresh Tabs")
    }

    override fun launchLoginView() = replaceWith(Git4JiraCredentialsView::class, sizeToScene = true, centerOnScreen = true)

    override fun updateServiceIcons(project: Project?) {
        githubErrorImage.isVisible = project?.gitServiceConfig != null
        jiraErrorImage.isVisible = project?.projectManagementServiceConfig != null
        slackErrorImage.isVisible = project?.communicationServiceConfig != null
        teamCityErrorImage.isVisible = project?.continuousIntegrationServiceConfig != null
    }

    override fun updateGitIcon(valid: Boolean) {
        githubErrorImage.image = Image(if (valid) GIT_ICON else GIT_ERROR_ICON)
    }

    override fun updateJiraIcon(valid: Boolean) {
        jiraErrorImage.image = Image(if (valid) JIRA_ICON else JIRA_ERROR_ICON)
    }

    private fun assignAccelerators() {
        accelerators[KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN)] = { homeController.onEditButtonClick() }
        accelerators[KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN)] = { homeController.onPrintButtonClick() }
        accelerators[KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)] = { homeController.onLogoutButtonClick() }
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = HOME_VIEW_WIDTH
        primaryStage.minHeight = HOME_VIEW_HEIGHT
        val scale = Scale(sceneScalingFactor, sceneScalingFactor, 0.0, 0.0 )
        root.transforms.setAll(scale)
    }

}