package com.siliconvalleyoffice.git4jira.constant

import java.util.*

val resourceBundle = ResourceBundle.getBundle("Messages")
val ENCRYPTION_ERROR = resourceBundle.getString("BadCredentials")

val APP_NAME = "git4JIRA"
val ORIGIN_BROWSER = "Origin Browser"
val USER = "user"
val MESSAGE = "message"
val JSON_EXTENSIONS = "*.jpeg, *.png"
val JSON_EXTENSION_DESCRIPTION = "JSON Files"
val EMPTY = ""
val NONE = "NONE"

val MUST_PROVIDE_PROJECT_NAME = "Must Provide Project Name"
val SELECT_PROJECT_LOGO = "Select Project Logo"
val MUST_PROVIDE_PROJECT_LOGO = "Must Provide Project Logo"
val MUST_PROVIDE_VC = "Must Provide Version Control (Git) Type"
val MUST_PROVIDE_PM = "Must Provide Project Management (JIRA) Type"
val MUST_PROVIDE_COMMUNICATION = "Must Provide Communication Type, Select 'None' If Not Applicable"
val MUST_PROVIDE_CI = "Must Provide Continuous Integration Type, Select 'None' If Not Applicable"
val PROJECT_ALREADY_EXISTS = "Project Already Exists"