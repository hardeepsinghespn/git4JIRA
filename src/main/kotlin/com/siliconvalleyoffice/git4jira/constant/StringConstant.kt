package com.siliconvalleyoffice.git4jira.constant

import java.util.*

val resourceBundle = ResourceBundle.getBundle("Messages")
val ENCRYPTION_ERROR = resourceBundle.getString("BadCredentials")

/**
 * Service value name constants
 */
const val GITHUB_VAL = "GitHub"
const val JIRA_VAL = "Jira"
const val SLACK_VAL = "Slack"
const val TEAM_CITY_VAL = "Team City"

/**
 * App Constans
 */
const val APP_NAME = "git4JIRA"
const val ORIGIN_BROWSER = "Origin Browser"
const val USER = "user"
const val MESSAGE = "message"
const val EMPTY = ""
const val NONE = "NONE"

/**
 * Message Constants
 */
const val MUST_PROVIDE_PROJECT_NAME = "Must Provide Project Name"
const val SELECT_PROJECT_LOGO = "Select Project Logo"
const val NOT_VALID_FILE_PATH = "Not a valid File or File doesn't Exist"
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