package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.dagger.GitTabModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.util.CHECK_MARK_ICON
import com.siliconvalleyoffice.git4jira.util.GIT_TAB_VIEW
import com.siliconvalleyoffice.git4jira.util.QUESTION_MARK_ICON
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import tornadofx.*
import javax.inject.Inject

class GitTabView(private val projectName: String): View(), GitTab.View {

    @Inject
    lateinit var gitTabController: GitTab.Controller

    override val root: AnchorPane by fxml(GIT_TAB_VIEW)

    private val provider: ChoiceBox<String> by fxid("provider")
    private val baseUrl: TextField by fxid("baseUrl")
    private val baseUrlValidationButton: Button by fxid("baseUrlValidation")
    private val baseUrlValidationIcon: javafx.scene.image.ImageView by fxid("baseUrlValidationIcon")

    private val accountName: TextField by fxid("accountName")
    private val password: TextField by fxid("password")
    private val credentialsValidationButton: Button by fxid("credentialsValidation")
    private val credentialsValidationIcon: ImageView by fxid("credentialsValidationIcon")

    private val rootDirectory: TextField by fxid("rootDirectory")

    init {
        Injector.Instance.appComponent.plus(GitTabModule(this)).inject(this)

        initializeView()
        assignListener()
    }

    private fun initializeView() {
        provider.items = FXCollections.observableArrayList(gitTabController.gitProviderItems())
        provider.selectionModel.selectFirst()

        val project = gitTabController.project()
        rootDirectory.text = project?.projectRootDirectoryPath ?: "No Path Found!"
        val gitCredentials = project?.gitService?.credentials
        updateCredentialsValidationIcon(gitCredentials?.isValid == true)
        accountName.text = gitCredentials?.username ?: ""
        password.text = gitCredentials?.password ?: ""
    }

    private fun assignListener() {
        baseUrlValidationButton.setOnMouseClicked { gitTabController.onBaseUrlValidationClicked(provider.value, baseUrl.text) }
        credentialsValidationButton.setOnMouseClicked { gitTabController.onCredentialsValidationClicked(accountName.text, password.text) }
    }

    override fun projectName() = projectName

    override fun updateBaseUrlValidationIcon(valid: Boolean) {
        baseUrlValidationIcon.image = Image(if(valid) CHECK_MARK_ICON else QUESTION_MARK_ICON)
    }

    override fun updateCredentialsValidationIcon(valid: Boolean) {
        credentialsValidationIcon.image = Image(if(valid) CHECK_MARK_ICON else QUESTION_MARK_ICON)
    }
}