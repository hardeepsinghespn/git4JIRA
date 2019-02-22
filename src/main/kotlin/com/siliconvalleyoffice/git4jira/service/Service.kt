package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.*

/**
 * A Base Service for all Project Services
 */
interface Service {

    interface BaseService {
        var name: String
        val logo: String
    }

    interface JsonFiles {

        var userConfig: UserConfig

        fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean

        fun updateUserConfig(username: String?, encryptionPhrase: String?, encryptionKey: String?)

        fun addProject(project: Project)

        fun removeProject(projectName: String)

        fun updateProject(projectName: String, project: Project)
    }

    interface Login {

        fun login(username: String, password: String): Pair<User?, CustomError?>

        fun logout()
    }
}