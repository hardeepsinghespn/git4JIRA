package com.siliconvalleyoffice.git4jira.view.tab

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.constant.NO_PATH_FOUND
import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.contract.ProjectConfiguration
import com.siliconvalleyoffice.git4jira.dagger.GitTabModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.model.GitServiceConfig
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.service.GitType
import com.siliconvalleyoffice.git4jira.util.CHECK_MARK_ICON
import com.siliconvalleyoffice.git4jira.util.GIT_TAB_VIEW
import com.siliconvalleyoffice.git4jira.util.QUESTION_MARK_ICON
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import tornadofx.*
import javax.inject.Inject

class GitTabView(private val profileConfigurationView: ProjectConfiguration.View, private val projectName: String) : View(), GitTab.View {

    @Inject
    lateinit var gitTabController: GitTab.Controller

    override val root: Parent by fxml(GIT_TAB_VIEW)

    private val provider: ChoiceBox<String> by fxid("provider")
    private val type: ChoiceBox<String> by fxid("type")
    private val baseUrl: TextField by fxid("baseUrl")
    private val accountName: TextField by fxid("accountName")
    private val password: TextField by fxid("password")
    private val validationButton: Button by fxid("validationButton")
    private val validationIcon: ImageView by fxid("validationIcon")
    private val rootDirectory: TextField by fxid("rootDirectory")

    init {
        Injector.Instance.appComponent.plus(GitTabModule(this)).inject(this)

        initializeView()
        assignListeners()
    }

    private fun initializeView() {
        val project = gitTabController.project()
        val requestInfo = project?.gitServiceConfig?.requestInfo

        //Init Provider
        provider.items = FXCollections.observableArrayList(gitTabController.gitProviderItems())
        provider.selectionModel.select(project?.gitServiceConfig?.gitServiceEnum?.name)

        //Init GitType
        initGitType(project)

        //Init TextFields
        provider.selectionModel.select(project?.gitServiceConfig?.gitServiceEnum?.name)
        if(requestInfo?.baseUrl?.isNotBlank() == true) baseUrl.text = requestInfo.baseUrl
        accountName.text = requestInfo?.username ?: EMPTY
        password.text = requestInfo?.password ?: EMPTY

        //Init Root Directory
        rootDirectory.text = project?.projectRootDirectoryPath ?: NO_PATH_FOUND

        //Init Icon
        updateValidationIcon(project?.gitServiceConfig, requestInfo?.credentialsValid == true)
    }

    private fun initGitType(project: Project?) {
        type.items = FXCollections.observableArrayList(GitType.values().map { it.name })
        val gitType = project?.gitServiceConfig?.requestInfo?.gitType
        if (gitType != null) {
            type.selectionModel.select(gitType.name)
        } else {
            type.selectionModel.selectFirst()
        }
        updateBaseUrl(GitType.valueOf(type.selectionModel.selectedItem))
    }

    private fun assignListeners() {
        type.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            if (newValue != null) gitTabController.onTypeSelectionChanged(newValue)
        }

        validationButton.setOnMouseClicked {
            gitTabController.onValidationClicked(
                    provider.value ?: EMPTY,
                    type.value ?: EMPTY,
                    baseUrl.text ?: EMPTY,
                    accountName.text ?: EMPTY,
                    password.text ?: EMPTY
            )
        }
    }

    override fun projectName() = projectName

    override fun updateValidationIcon(gitServiceConfig: GitServiceConfig?, valid: Boolean) {
        validationIcon.image = Image(if (valid) CHECK_MARK_ICON else QUESTION_MARK_ICON)
        val gitServiceEnum = gitServiceConfig?.gitServiceEnum
        profileConfigurationView.updateGitTabIcon(if(valid) gitServiceEnum?.serviceLogo else gitServiceEnum?.serviceErrorLog)
    }

    override fun updateBaseUrl(gitType: GitType?) {
        baseUrl.text = gitType?.url
        baseUrl.isDisable = gitType?.isPublic() == true
    }

    override fun updateAccountName(name: String) {
        accountName.text = name
    }

    override fun disableValidationButton(disable: Boolean) {
        validationButton.isDisable = disable
    }
}