package com.siliconvalleyoffice.git4jira.services

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.app.CONFIG
import com.siliconvalleyoffice.git4jira.app.Git4JiraApp
import com.siliconvalleyoffice.git4jira.app.PROJECT_PROFILE
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.models.Configuration
import com.siliconvalleyoffice.git4jira.models.ProjectProfileData

class JsonFilesService(val gson: Gson): Service.JsonFiles {


    override fun getConfiguration() = Gson().fromJson(Git4JiraApp::class.java.getResource(CONFIG).readText(), Configuration::class.java)

    override fun getProjectProfileData() = Gson().fromJson(Git4JiraApp::class.java.getResource(PROJECT_PROFILE).readText(), ProjectProfileData::class.java)
}