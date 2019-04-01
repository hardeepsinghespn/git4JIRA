package com.siliconvalleyoffice.git4jira.constant

import java.util.*

val resourceBundle = ResourceBundle.getBundle("Messages")
val ENCRYPTION_ERROR = resourceBundle.getString("BadCredentials")

/**
 * Interceptor constants
 */
const val NO_AUTHENTICATION_HEADER = "No-Authentication"
const val AUTHORIZATION_HEADER = "Authorization"

/**
 * Service value name constants
 */
const val GITHUB_VAL = "GitHub"
const val JIRA_VAL = "Jira"
const val SLACK_VAL = "Slack"
const val TEAM_CITY_VAL = "Team City"

/**
 * App Constants
 */
const val APP_NAME = "Git4Jira"
const val MESSAGE = "message"
const val EMPTY = ""
const val NONE = "NONE"
const val NO_PATH_FOUND = "No Path Found!"
const val BREAK = "|"

/**
 * Button Constants
 */
const val EDIT = "Edit"
const val DELETE = "Delete"
const val UPDATE = "Update"

/**
 * Message Constants
 */
const val MUST_PROVIDE_PROJECT_NAME = "Must Provide Project Name"
const val SELECT_PROJECT_LOGO = "Select Project Logo"
const val NOT_VALID_FILE_PATH = "Not a credentialsValid File or File doesn't Exist"
const val MUST_PROVIDE_VC = "Must Provide Version Control (Git) Type"
const val MUST_PROVIDE_PM = "Must Provide Project Management (JIRA) Type"
const val MUST_PROVIDE_COMMUNICATION = "Must Provide Communication Type, Select 'None' If Not Applicable"
const val MUST_PROVIDE_CI = "Must Provide Continuous Integration Type, Select 'None' If Not Applicable"
const val PROJECT_ALREADY_EXISTS = "Project Already Exists"
const val LOGO_FILE_COPY_SUCCESS = "Logo File Copied Successfully!"
const val LOGO_FILE_COPY_ERROR = "Error Occurred while copying the projectLogo file (Defaulting to Original Destination): "
const val USER_CONFIG_FOUND = "User Config Found!"
const val USER_CONFIG_CREATED_UPDATED = "User Config Created/Updated!"
const val LOGO_FILE_REMOVE_SUCCESS = "Logo File Remove Successful"
const val LOGO_FILE_REMOVE_FAILED = "Logo File Remove Failed"
const val DELETE_PROJECT = "Delete Project"
const val DELETE_DIALOG_MESSAGE = "All data associated with this project will be deleted, including project directory and credentials"
const val MUST_SELECT_GIT_PROVIDE = "Must Select a GIT Provider"
const val MUST_SELECT_GIT_TYPE = "Must Select a GIT Type"
const val MUST_PROVIDE_BASE_URL = "Must Provide Base Url"
const val MUST_PROVIDE_ACCOUNT_NAME = "Must Provide AccountName/UserName"
const val MUST_PROVIDE_PASSWORD = "Must Provide Password/PassKey"
const val INVALID_BASE_URL = "Invalid Base Url\nExample: https://host.companyname.com"
const val INVALID_CREDENTIALS = "Invalid Credentials"
const val UNABLE_TO_FIND_BOARDS = "Unable to find Boards"
const val PROJECT_NAME_REQUIRED = "Project Name is Required"
const val PROJECT_NAME_REQUIRE_MORE_THAN_THREE_CHAR = "Please enter more than 3 Chars to Search"
