package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.dagger.GitTabModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.util.GIT_TAB_VIEW
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.*

class GitTabView: View(), GitTab.View {

    override val root: AnchorPane by fxml(GIT_TAB_VIEW)

    private val provider: ChoiceBox<String> by fxid("provider")
    private val baseUrl: TextField by fxid("baseUrl")
    private val baseUrlValidationButton: Button by fxid("baseUrlValidation")

    private val accountName: TextField by fxid("accountName")
    private val password: TextField by fxid("password")
    private val credentialsValidationButton: Button by fxid("credentialsValidation")

    private val rootDirectory: TextField by fxid("rootDirectory")

    init {
        Injector.Instance.appComponent.plus(GitTabModule(this)).inject(this)
    }
}