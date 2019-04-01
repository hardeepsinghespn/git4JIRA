package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.contract.JiraTab
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.BOARD
import com.siliconvalleyoffice.git4jira.util.prepareJiraApiUrl
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import okhttp3.Credentials as OkHttpCredentials


class JiraTabController(private val jiraTabView: JiraTab.View,
                        private val jsonFilesService: Service.JsonFiles
) : JiraTab.Controller {

    var project: Project? = null

    init {
        project = jsonFilesService.getProject(jiraTabView.projectName() ?: "")
    }

    override fun project() = project

    override fun onValidationButtonClick(baseUrl: String, accountName: String, password: String) {
        if(validateCredentialInfo(baseUrl, accountName, password)) {
            val baseBoardJiraUrl = baseUrl.prepareJiraApiUrl() + BOARD
            val token = OkHttpCredentials.basic(accountName, password)

            val projectManagementServiceConfig = project?.projectManagementServiceConfig
            projectManagementServiceConfig?.jiraService()?.validate(baseBoardJiraUrl, token)
                    ?.subscribe({
                        val requestInfo = RequestInfo(baseUrl, accountName, password, credentialsValid = true)
                        project?.projectManagementServiceConfig?.requestInfo = requestInfo
                        jiraTabView.updateValidationIcon(projectManagementServiceConfig, true)
                        jsonFilesService.updateProject(project)
                    }, {
                        project?.projectManagementServiceConfig?.requestInfo?.credentialsValid = false
                        jsonFilesService.updateProject(project)
                        jiraTabView.updateBoardValidationIcon(false)
                        showMessageDialog(INVALID_CREDENTIALS)
                    })

        }
    }

    override fun onBoardSearchClick(projectName: String) {
        if(validateBoardInfo(projectName)) {
            project?.projectManagementServiceConfig?.jiraService()?.boardByName(projectName)
                    ?.subscribe({ boardResponse ->
                        jiraTabView.updateBoardChoiceBox(boardResponse.values.map { "${it.id}|${it.name}" })
                    }, {
                        jiraTabView.updateBoardChoiceBox(null)
                        showMessageDialog(UNABLE_TO_FIND_BOARDS)
                    })
        }
    }

    override fun onBoardSelectionChanged(newValue: String) {
        val split = newValue.split(BREAK)
        project?.projectManagementServiceConfig?.requestInfo?.boardId = split.first()
        project?.projectManagementServiceConfig?.requestInfo?.boardName = split.last()
        project?.projectManagementServiceConfig?.requestInfo?.boardValid = true
        jsonFilesService.updateProject(project)
        jiraTabView.updateBoardValidationIcon(true)
    }

    private fun validateCredentialInfo(baseUrl: String, accountName: String, password: String): Boolean {
        if (baseUrl.isBlank()) {
            showMessageDialog(MUST_PROVIDE_BASE_URL)
            return false
        }

        if (accountName.isBlank()) {
            showMessageDialog(MUST_PROVIDE_ACCOUNT_NAME)
            return false
        }

        if (password.isBlank()) {
            showMessageDialog(MUST_PROVIDE_PASSWORD)
            return false
        }
        return true
    }

    private fun validateBoardInfo(projectName: String): Boolean {
        if(projectName.isEmpty()) {
            showMessageDialog(PROJECT_NAME_REQUIRED)
            return false
        }

        if(projectName.length <= 3) {
            showMessageDialog(PROJECT_NAME_REQUIRE_MORE_THAN_THREE_CHAR)
            return false
        }
        return true
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK)
        alert.showAndWait()
    }
}