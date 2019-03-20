package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.model.*

/**
 * A Base Service for all Project Services
 */
interface Service {

    interface JsonFiles {

        var userConfig: UserConfig

        fun retrieveUserConfig()

        fun updateLastSelectedProject(projectName: String)

        fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean

        fun projectNames(): List<String>

        fun getLastSelectedProject(): Project?

        fun getProject(projectName: String): Project?

        fun addProject(project: Project)

        fun removeProject(projectName: String)

        fun updateProject(project: Project?)
    }

    interface Login {

        fun login(username: String, password: String): Pair<User?, CustomError?>

        fun logout()
    }
}