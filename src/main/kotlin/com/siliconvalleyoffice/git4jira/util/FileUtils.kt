package com.siliconvalleyoffice.git4jira.util

import java.io.File

/**
 * Extensions
 */
const val JPEG_EXT = "*.jpeg"
const val PNG_EXT = "*.png"
const val JPG_EXT = "*.jpg"
const val IMAGE_EXTENSION_DESCRIPTION = "Image Files"

/**
 * Assets
 */
const val PROJECT_LOGO_DIR = "src/main/resources/assets/projectLogo/"
const val USER_CONFIG = "src/main/resources/assets/config/user_config.json"

/**
 * View Paths
 */
const val CREDENTIALS_VIEW = "/view/Git4JiraCredentialsView.fxml"
const val HOME_VIEW = "/view/HomeView.fxml"
const val PROJECT_PROFILE_VIEW = "/view/ProjectProfileView.fxml"
const val CREATE_PROJECT_DIALOG_VIEW = "/view/CreateProjectView.fxml"
const val GIT_TAB_VIEW = "/view/GitTabView.fxml"


/**
 * Check if File is Image File based on extension {JPEG, PNG, JPG}
 */
fun File.isValidImageExtension() = extension.contains(JPEG_EXT) || extension.contains(PNG_EXT) || extension.contains(JPG_EXT)
