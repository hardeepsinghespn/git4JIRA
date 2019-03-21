package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.model.GitServiceConfig
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.GitServiceEnum
import com.siliconvalleyoffice.git4jira.service.GitType
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.USER
import com.siliconvalleyoffice.git4jira.util.prepareAPIV3Url
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import java.net.URL
import okhttp3.Credentials as OkHttpCredentials

class GitTabController(private val gitTabView: GitTab.View,
                       private val jsonFilesService: Service.JsonFiles
) : GitTab.Controller {

    var project: Project? = null

    init {
        project = jsonFilesService.getProject(gitTabView.projectName() ?: "")
    }

    override fun gitProviderItems() = GitServiceEnum.values().map { it.name }

    override fun project() = project

    override fun onTypeSelectionChanged(newValue: String) {
        gitTabView.updateBaseUrl(GitType.valueOf(newValue))
    }

    override fun onValidationClicked(provider: String, gitTypeName: String, baseUrl: String, accountName: String, password: String) {
        if (validateInformation(provider, gitTypeName, baseUrl, accountName, password)) {
            val token = OkHttpCredentials.basic(accountName, password)
            val gitType = GitType.valueOf(gitTypeName)
            val sanitizeBaseUrl = if (gitType.isEnterprise()) baseUrl.prepareAPIV3Url() else baseUrl

            val gitServiceConfig = project?.gitServiceConfig
            gitServiceConfig?.gitService()?.validate(sanitizeBaseUrl + USER, token)
                    ?.doOnSubscribe { gitTabView.disableValidationButton(true) }
                    ?.doFinally { gitTabView.disableValidationButton(false) }
                    ?.subscribe({
                        gitServiceConfig.gitServiceEnum = GitServiceEnum.valueOf(provider)
                        val requestInfo = RequestInfo(gitType, baseUrl, accountName, password, true)
                        project?.gitServiceConfig = GitServiceConfig(GitServiceEnum.valueOf(provider), requestInfo)
                        jsonFilesService.updateProject(project)

                        gitTabView.updateValidationIcon(gitServiceConfig, true)
                        println("Authentication Successful")
                    }, {
                        gitServiceConfig.requestInfo?.valid = false

                        gitTabView.updateValidationIcon(gitServiceConfig, false)
                        showMessageDialog(INVALID_CREDENTIALS)
                        println("Authentication Failed")
                    })
        }
    }

    private fun validateInformation(provider: String, gitTypeName: String, baseUrl: String, accountName: String, password: String): Boolean {
        if (provider.isBlank()) {
            showMessageDialog(MUST_SELECT_GIT_PROVIDE)
            return false
        }

        if (gitTypeName.isBlank()) {
            showMessageDialog(MUST_SELECT_GIT_TYPE)
            return false
        }

        if (baseUrl.isBlank()) {
            showMessageDialog(MUST_PROVIDE_BASE_URL)
            return false
        }

        try {
            URL(baseUrl).content
        } catch (e: Exception) {
            showMessageDialog(INVALID_BASE_URL)
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

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK)
        alert.showAndWait()
    }
}