package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.Configuration
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.ProjectProfileData
import com.siliconvalleyoffice.git4jira.model.User

/**
 * A Base Service for all Project Services
 */
interface Service {

    interface BaseService {
        var name: String
        val logo: String
    }

    interface JsonFiles {

        var configuration: Configuration

        var projectProfileData: ProjectProfileData

        fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean

        fun addProject(project: Project)

        fun removeProject(projectName: String)

        fun editProject(projectName: String)
    }

    interface Login {

        fun login(username: String, password: String): Pair<User?, Error?>

        fun logout()
    }
}