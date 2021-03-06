package com.siliconvalleyoffice.git4jira

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.PROJECT_DIR_PATH
import com.siliconvalleyoffice.git4jira.view.HomeView
import tornadofx.*
import java.io.File
import javax.inject.Inject

class Git4JiraApp : App(HomeView::class) {

    @Inject
    lateinit var jsonFileService: Service.JsonFiles

    init {
        Injector.Instance.initialize()
        Injector.Instance.appComponent.inject(this)

        initProjectDirectory()
        initJsonFileService()
    }

    /**
     * Initialize Project Directory
     * Creates a project directory in gitHubUserResponse home folder, if its already not present
     * Fetch information if directory is already available
     */
    private fun initProjectDirectory() {
        val projectDirectory = File(PROJECT_DIR_PATH)
        if (!projectDirectory.exists()) {
            println("Creating Project Directory: ${projectDirectory.path}")
            projectDirectory.mkdir()
        } else {
            println("Project Directory Found")
        }
    }

    /**
     * Retrieve UserConfig
     */
    private fun initJsonFileService() {
        jsonFileService.retrieveUserConfig()
    }
}