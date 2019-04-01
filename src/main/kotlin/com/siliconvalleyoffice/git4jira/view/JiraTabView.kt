package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contract.JiraTab
import com.siliconvalleyoffice.git4jira.contract.ProjectProfile
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.JiraTabModule
import com.siliconvalleyoffice.git4jira.model.ProjectManagementServiceConfig
import com.siliconvalleyoffice.git4jira.util.CHECK_MARK_ICON
import com.siliconvalleyoffice.git4jira.util.JIRA_TAB_VIEW
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

class JiraTabView(private val profileProfileView: ProjectProfile.View, private val projectName: String): View(), JiraTab.View {

    @Inject
    lateinit var jiraTabController: JiraTab.Controller

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

        initializeView()
        assignListeners()
    }

    override fun projectName() = projectName

    private fun initializeView() {
        val project = jiraTabController.project()
        val requestInfo = project?.projectManagementServiceConfig?.requestInfo

        baseUrl.text = requestInfo?.baseUrl
        accountName.text = requestInfo?.username
        password.text = requestInfo?.password
        val valid = requestInfo?.credentialsValid == true
        updateValidationIcon(project?.projectManagementServiceConfig, valid)

        if(valid) {
            boardName.text = requestInfo?.boardName
            boardName.isDisable = false
            boardSearchButton.isDisable = false
        }

        boardChoiceBox.isDisable = true
        updateBoardValidationIcon(requestInfo?.boardValid == true)
    }

    private fun assignListeners() {
        validationButton.setOnMouseClicked { jiraTabController.onValidationButtonClick(baseUrl.text, accountName.text, password.text) }
        boardSearchButton.setOnMouseClicked { jiraTabController.onBoardSearchClick(boardName.text) }
        boardChoiceBox.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            if(newValue != null) jiraTabController.onBoardSelectionChanged(newValue)
        }
    }

    override fun updateValidationIcon(projectManagementServiceConfig: ProjectManagementServiceConfig?, valid: Boolean) {
        validationIcon.image = Image(if (valid) CHECK_MARK_ICON else QUESTION_MARK_ICON)
        val projectManagementEnum = projectManagementServiceConfig?.projectManagementEnum
        profileProfileView.updateContinuousIntegrationTabIcon(if(valid) projectManagementEnum?.serviceLogo else projectManagementEnum?.serviceErrorLog)
        boardName.isDisable = !valid
        boardSearchButton.isDisable = !valid
    }

    override fun updateBoardValidationIcon(valid: Boolean) {
        boardValidationIcon.image = Image(if (valid) CHECK_MARK_ICON else QUESTION_MARK_ICON)
    }

    override fun updateBoardChoiceBox(boardList: List<String>?) {
        val listEmpty = boardList?.isEmpty() == true
        boardChoiceBox.isDisable = listEmpty
        if(!listEmpty) {
            boardChoiceBox.items = FXCollections.observableArrayList(boardList)
            boardChoiceBox.show()
        }
    }
}