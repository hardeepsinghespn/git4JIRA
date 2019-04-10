package com.siliconvalleyoffice.git4jira.service.json

import com.siliconvalleyoffice.git4jira.constant.*
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.UserConfig
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.util.PROJECT_DIR_PATH
import com.siliconvalleyoffice.git4jira.util.USER_CONFIG
import com.squareup.moshi.Moshi
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import java.io.File
import java.io.FileWriter

class JsonFileService(val moshi: Moshi) : Service.JsonFiles {

    override lateinit var userConfig: UserConfig


    override fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean {
        return encryptionPhrase == "Test Phrase" && encryptionKey == "TestKey"
    }

    override fun retrieveUserConfig() {
        val userConfigJson = File(USER_CONFIG)
        if (userConfigJson.exists()) {
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

    override fun updateLastSelectedProject(projectName: String) {
        userConfig.lastSelection = projectName
    }

    private fun writeUserConfig() {
        val fileWriter = FileWriter(USER_CONFIG)
        val userConfigJson = moshi.adapter(UserConfig::class.java).toJson(userConfig)
        fileWriter.write(userConfigJson)
        fileWriter.flush()
        fileWriter.close()
        println(USER_CONFIG_CREATED_UPDATED)
    }

    override fun projectNames(): List<String> {
        retrieveIfNotInitialized()
        return userConfig.project.map { it.name }
    }

    override fun addProject(project: Project) {
        retrieveIfNotInitialized()
        project.projectRootDirectoryPath = createDirectoryInProjectDir(project.name).path
        project.logo = copyLogoFile(project.logo, project.projectRootDirectoryPath)?.path ?: EMPTY
        userConfig.project.add(project)
        writeUserConfig()
    }

    override fun getLastSelectedProject(): Project? {
        retrieveIfNotInitialized()
        if (userConfig.project.isEmpty()) return null
        return userConfig.project.firstOrNull { it.name == userConfig.lastSelection } ?: userConfig.project.first()
    }

    override fun getProject(projectName: String): Project? {
        retrieveIfNotInitialized()
        return userConfig.project.firstOrNull { it.name.toLowerCase() == projectName.toLowerCase() }
    }

    override fun removeProject(projectName: String) {
        retrieveIfNotInitialized()
        val project = userConfig.project.find { it.name == projectName }
        userConfig.project.remove(project)

        removeDirectoryFromProjectDir(projectName)
        removeImageFile(project?.logo)
        writeUserConfig()
    }

    override fun updateProject(project: Project?) {
        if (project != null) {
            retrieveIfNotInitialized()
            userConfig.project.removeIf { it.name == project.name }
            userConfig.project.add(project)
            writeUserConfig()
        }
    }

    override fun updateProjectWithCopy(project: Project?) {
        if (project != null) {
            retrieveIfNotInitialized()
            userConfig.project.removeIf { it.name == project.name }
            project.logo = copyLogoFile(project.logo, project.projectRootDirectoryPath)?.path ?: EMPTY
            userConfig.project.add(project)
            writeUserConfig()
        }
    }

    private fun retrieveIfNotInitialized() {
        if (!::userConfig.isInitialized) retrieveUserConfig()
    }

    private fun removeImageFile(logoPath: String?) {
        if (File(logoPath).delete()) println(LOGO_FILE_REMOVE_SUCCESS) else println(LOGO_FILE_REMOVE_FAILED)
    }


    /**
     * Project Directory File/Folder Operations
     */
    private fun createDirectoryInProjectDir(projectName: String): File {
        val projectRootDirectory = File(PROJECT_DIR_PATH + projectName + File.separator)
        if (!projectRootDirectory.exists()) projectRootDirectory.mkdir()
        return projectRootDirectory
    }

    /**
     * Copy the File to '${ProjectDir}/projectLogo'
     */
    private fun copyLogoFile(originalLogoFile: String, destinationRoot: String?): File? {
        val sourceLogoFile = File(originalLogoFile)

        return when {
            destinationRoot == null -> {
                showMessageDialog(ROOT_DIRECTORY_NOT_FOUND)
                null
            }
            originalLogoFile.startsWith(destinationRoot) -> {
                println(LOGO_EXISTS)
                File(originalLogoFile)
            }
            else -> try {
                val targetFile = sourceLogoFile.copyTo(File(destinationRoot + File.separator + sourceLogoFile.name), true)
                println(LOGO_FILE_COPY_SUCCESS)
                targetFile
            } catch (e: Exception) {
                println(LOGO_FILE_COPY_ERROR)
                showMessageDialog(LOGO_FILE_COPY_ERROR)
                null
            }
        }
    }

    private fun showMessageDialog(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION, message, ButtonType.CANCEL)
        alert.showAndWait()
    }

    private fun removeDirectoryFromProjectDir(projectName: String) = File(PROJECT_DIR_PATH + projectName + File.separator).delete()
}