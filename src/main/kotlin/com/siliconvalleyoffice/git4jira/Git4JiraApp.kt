package com.siliconvalleyoffice.git4jira

import com.siliconvalleyoffice.git4jira.api.GitAuthInterceptor
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.model.Credentials
import com.siliconvalleyoffice.git4jira.service.*
import tornadofx.*
import view.Git4JiraCredentialsView
import javax.inject.Inject

class Git4JiraApp : App(Git4JiraCredentialsView::class) {

    @Inject
    lateinit var jsonFileService: Service.JsonFiles

    @Inject
    lateinit var gitAuthInterceptor: GitAuthInterceptor

    init {
        Injector.Instance.initialize()
        Injector.Instance.appComponent.inject(this)

        loadUserConfig()
    }

    private fun loadUserConfig() {
        jsonFileService.retrieveUserConfig()

        //Define Git Credentials
        gitAuthInterceptor.setCredentials(jsonFileService.getLastSelectedProject()?.gitService?.credentials)
    }

}