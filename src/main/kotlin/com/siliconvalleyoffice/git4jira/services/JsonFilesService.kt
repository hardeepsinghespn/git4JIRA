package com.siliconvalleyoffice.git4jira.services

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.app.CONFIG
import com.siliconvalleyoffice.git4jira.app.Git4JiraApp
import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.models.Configuration
import com.siliconvalleyoffice.git4jira.models.Project
import com.siliconvalleyoffice.git4jira.models.ProjectProfileData

class JsonFilesService(val gson: Gson) : Service.JsonFiles {

    override lateinit var configuration: Configuration
    override lateinit var projectProfileData: ProjectProfileData

    init {
        retrieveConfiguration()
        retrieveProjectProfileData()
    }

    override fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean {
        return encryptionPhrase.equals("Test phrase") && encryptionKey.equals("TestKey")
    }

    private fun retrieveConfiguration() {
        configuration = gson.fromJson(Git4JiraApp::class.java.getResource(CONFIG).readText(), Configuration::class.java)
    }

    private fun retrieveProjectProfileData() {
        projectProfileData = gson.fromJson(Git4JiraApp::class.java.getResource(PROJECT_PROFILE).readText(), ProjectProfileData::class.java)
    }

    override fun addProject(project: Project) {
        if (!::projectProfileData.isInitialized) retrieveProjectProfileData()
        projectProfileData.projects.add(project)
    }

    override fun removeProject(projectName: String) {
        if(!::projectProfileData.isInitialized) retrieveProjectProfileData()
        projectProfileData.projects.removeIf { it.name == projectName }
    }

    override fun editProject(projectName: String) {
        if(!::projectProfileData.isInitialized) retrieveProjectProfileData()
        projectProfileData.projects.removeIf { it.name == projectName }
    }
}