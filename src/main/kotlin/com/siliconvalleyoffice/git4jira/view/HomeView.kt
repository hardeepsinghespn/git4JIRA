package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.HOME_VIEW
import com.siliconvalleyoffice.git4jira.contracts.Home
import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.dagger.HomeModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import tornadofx.*
import javax.inject.Inject
import kotlin.properties.ReadOnlyProperty
import javafx.scene.control.ButtonType
import sun.plugin2.main.server.LiveConnectSupport.getResult
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Alert
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination


class HomeView: View(), Home.View {

    @Inject
    lateinit var homeController: Home.Controller

    override val root: AnchorPane by fxml(HOME_VIEW)

    val logoImageView: ImageView by fxid("projectLogoImageView")
    val profileChoiceBox: ChoiceBox<String> by fxid("profilePicker")

    val githubErrorImage: ImageView by fxid("githubErrorImage")
    val jiraErrorImage: ImageView by fxid("jiraErrorImage")
    val slackErrorImage: ImageView by fxid("slackErrorImage")

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
        assignButtonListeners()
    }

    private fun assignButtonListeners() {
        editButton.setOnMouseClicked { showMessageDialog("Edit Profile") }
        printButton.setOnMouseClicked { showMessageDialog("Print Profile") }
        logoutButton.setOnMouseClicked { showMessageDialog("Exit Profile") }

        githubErrorImage.setOnMouseClicked { showMessageDialog("GitHub Error") }
        jiraErrorImage.setOnMouseClicked { showMessageDialog("Jira Error") }
        slackErrorImage.setOnMouseClicked { showMessageDialog("Slack Error") }
    }

    private fun assignAccelerators() {
        accelerators[KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN)] = { showMessageDialog("Edit Profile") }
        accelerators[KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN)] = { showMessageDialog("Print Profile") }
        accelerators[KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)] = { showMessageDialog("Exit Profile") }
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}