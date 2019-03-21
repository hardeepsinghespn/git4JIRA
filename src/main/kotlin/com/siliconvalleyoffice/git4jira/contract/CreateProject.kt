package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Project
import java.io.File

interface CreateProject {

    interface View {

        fun updateProjectLogoPath(logoFile: File?)

        fun closeView()

        fun versionControlSelection(): String

        fun projectManagementSelection(): String

        fun communicationSelection(): String

        fun continuousIntegrationSelection(): String

        fun projectName(): String

        fun projectLogo(): String
    }

    interface Controller {

        fun project(projectName: String): Project?

        fun versionControlItems(): List<String>

        fun projectManagementItems(): List<String>

        fun communicationItems(): List<String>

        fun continuousIntegrationItems(): List<String>

        fun onBrowseClick()

        fun onUpdateClick(projectName: String)

        fun onCreateClick()

        fun onCancelClick()
    }
}