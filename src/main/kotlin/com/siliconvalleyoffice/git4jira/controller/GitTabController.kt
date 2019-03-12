package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.service.GitServiceEnum
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.HTTPS
import com.siliconvalleyoffice.git4jira.util.SLASH
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

    override fun onBaseUrlValidationClicked(provider: String, baseUrl: String) {
        val url = HTTPS + baseUrl + SLASH
        val requestInfo = project?.gitService?.requestInfo
        requestInfo?.baseUrl = url

        project?.gitService?.gitServiceEnum?.service?.validateBaseUrl(url)?.subscribe({
            requestInfo?.baseUrlValid = true
            gitTabView.updateBaseUrlValidationIcon(true)
        }, {
            requestInfo?.baseUrlValid = false
            gitTabView.updateBaseUrlValidationIcon(false)
        })
        jsonFilesService.updateProject(project)
    }

    override fun onCredentialsValidationClicked(accountName: String, password: String) {
        val token = OkHttpCredentials.basic(accountName, password)
        val requestInfo = project?.gitService?.requestInfo
        requestInfo?.username = accountName
        requestInfo?.password = password

        project?.gitService?.gitServiceEnum?.service?.validate(token)?.subscribe({
            requestInfo?.credentialsValid = true
            gitTabView.updateCredentialsValidationIcon(true)
        }, {
            gitTabView.updateCredentialsValidationIcon(false)
            requestInfo?.credentialsValid = false
        })

        jsonFilesService.updateProject(project)
    }
}