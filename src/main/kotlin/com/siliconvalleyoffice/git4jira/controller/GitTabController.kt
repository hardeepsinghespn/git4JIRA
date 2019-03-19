package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.model.GitBaseUrl
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.GitServiceEnum
import com.siliconvalleyoffice.git4jira.service.GitType
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.*
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import java.net.URL
import okhttp3.Credentials as OkHttpCredentials

class GitTabController(private val gitTabView: GitTab.View,
                       private val jsonFilesService: Service.JsonFiles,
                       private val gitBaseUrl: GitBaseUrl
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

            //Authenticate Credentials
            project?.gitService?.gitServiceEnum?.service?.validate(sanitizeBaseUrl, token)
                    ?.doOnSubscribe { gitTabView.disableValidationButton(true) }
                    ?.doFinally { gitTabView.disableValidationButton(false) }
                    ?.subscribe({
                        project?.gitService?.gitServiceEnum = GitServiceEnum.valueOf(provider)
                        project?.gitService?.gitType = gitType
                        project?.gitService?.requestInfo = RequestInfo(baseUrl, accountName, password, true)
                        jsonFilesService.updateProject(project)

                        gitBaseUrl.url = sanitizeBaseUrl
                        gitTabView.updateValidationIcon(true)
                    }, {
                        project?.gitService?.requestInfo?.valid = false
                        gitTabView.updateValidationIcon(false)
                        //Todo: Error Handling Pending
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
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }
}