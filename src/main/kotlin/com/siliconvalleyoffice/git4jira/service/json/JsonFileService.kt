package com.siliconvalleyoffice.git4jira.service.json

import com.siliconvalleyoffice.git4jira.constant.LOGO_FILE_REMOVE_FAILED
import com.siliconvalleyoffice.git4jira.constant.LOGO_FILE_REMOVE_SUCCESS
import com.siliconvalleyoffice.git4jira.constant.USER_CONFIG_CREATED_UPDATED
import com.siliconvalleyoffice.git4jira.constant.USER_CONFIG_FOUND
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.UserConfig
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.USER_CONFIG
import com.squareup.moshi.Moshi
import java.io.File
import java.io.FileWriter

class JsonFileService(val moshi: Moshi) : Service.JsonFiles {

    override lateinit var userConfig: UserConfig

    init {
        retrieveUserConfig()
    }

    override fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean {
        return encryptionPhrase == "Test Phrase" && encryptionKey == "TestKey"
    }

    private fun retrieveUserConfig() {
        val userConfigJson = File(USER_CONFIG)
        if(userConfigJson.exists()) {
            readUserConfig(userConfigJson)
        } else {
            userConfig = UserConfig()
            writeUserConfig()
        }
    }

    private fun readUserConfig(userConfigJson: File) {
        userConfig = moshi.adapter(UserConfig::class.java).fromJson(userConfigJson.readText()) ?: UserConfig()
        println(USER_CONFIG_FOUND)
    }

    private fun writeUserConfig() {
        val fileWriter = FileWriter(USER_CONFIG)
        val userConfigJson = moshi.adapter(UserConfig::class.java).toJson(userConfig)
        fileWriter.write(userConfigJson)
        fileWriter.flush()
        fileWriter.close()
        println(USER_CONFIG_CREATED_UPDATED)
    }

    override fun updateUserConfig(username: String?, encryptionPhrase: String?, encryptionKey: String?) {
        retrieveIfNotInitialized()
        if(username != null) userConfig.username = username
        if(encryptionPhrase != null) userConfig.encryptionPhrase = encryptionPhrase
        if(encryptionKey != null) userConfig.encryptionKey = encryptionKey
        writeUserConfig()
    }

    override fun addProject(project: Project) {
        retrieveIfNotInitialized()
        userConfig.project.add(project)
        writeUserConfig()
    }

    override fun removeProject(projectName: String) {
        retrieveIfNotInitialized()
        val project = userConfig.project.find { it.name == projectName }
        userConfig.project.remove(project)
        removeImageFile(project?.logo)
        writeUserConfig()
    }

    override fun updateProject(projectName: String, project: Project) {
        retrieveIfNotInitialized()
        userConfig.project.removeIf { it.name == projectName }
        userConfig.project.add(project)
        writeUserConfig()
    }

    private fun retrieveIfNotInitialized() {
        if (!::userConfig.isInitialized) retrieveUserConfig()
    }

    private fun removeImageFile(logoPath: String?) {
        if(File(logoPath).delete()) println(LOGO_FILE_REMOVE_SUCCESS) else println(LOGO_FILE_REMOVE_FAILED)
    }
}