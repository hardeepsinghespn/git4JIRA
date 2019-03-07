package com.siliconvalleyoffice.git4jira

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.HomeView
import tornadofx.*
import javax.inject.Inject

class Git4JiraApp : App(HomeView::class) {

    @Inject
    lateinit var jsonFileService: Service.JsonFiles

    init {
        Injector.Instance.initialize()
        Injector.Instance.appComponent.inject(this)

        jsonFileService.retrieveUserConfig()

        verifyGitHubCrendentials()
    }

    private fun verifyGitHubCrendentials() {
        jsonFileService.getLastSelectedProject()?.gitService?.gitServiceEnum?.service?.authenticate()?.subscribe({
            println("GitHub Authentication Successful!")
        }, {
            println("GitHub Authentication Failed: $it")
        })
    }
}