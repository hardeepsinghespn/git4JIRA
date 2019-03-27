package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contract.JiraTab
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.JiraTabModule
import com.siliconvalleyoffice.git4jira.util.JIRA_TAB_VIEW
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import tornadofx.*

class JiraTabView : View(), JiraTab.View {

    override val root: Parent by fxml(JIRA_TAB_VIEW)

    private val baseUrl: TextField by fxid("baseUrl")
    private val accountName: TextField by fxid("accountName")
    private val password: TextField by fxid("password")
    private val validationIcon: ImageView by fxid("validationIcon")
    private val validationButton: Button by fxid("validationButton")

    private val boardName: TextField by fxid("boardName")
    private val boardChoiceBox: ChoiceBox<String> by fxid("availableBoards")
    private val boardValidationIcon: ImageView by fxid("boardValidationIcon")
    private val boardSearchButton: Button by fxid("boardSearchButton")

    init {
        Injector.Instance.appComponent.plus(JiraTabModule(this)).inject(this)
    }
}