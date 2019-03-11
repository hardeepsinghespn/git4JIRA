package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.model.Credentials
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.service.GitServiceEnum
import com.siliconvalleyoffice.git4jira.service.Service
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
        println("Provider: $provider")
        println("Base Url: $baseUrl")
    }

    override fun onCredentialsValidationClicked(accountName: String, password: String) {
        val token = OkHttpCredentials.basic(accountName, password)
        project?.gitService?.gitServiceEnum?.service?.validate(token)?.subscribe({
            println("Valid")
            project?.gitService?.credentials = Credentials(accountName, password, true)
            jsonFilesService.updateProject(project)
            gitTabView.updateCredentialsValidationIcon(true)
        }, {
            println("Invalid")
            gitTabView.updateCredentialsValidationIcon(false)
        })
    }
}