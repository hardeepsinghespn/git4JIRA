package com.siliconvalleyoffice.git4jira.contracts

interface CreateProject {

    interface View {

    }

    interface Controller {

        fun versionControlItems(): List<String>

        fun projectManagementItems(): List<String>

        fun communicationItems(): List<String>

        fun continuousIntegrationItems(): List<String>

        fun onBrowseClick()

        fun onCreateClick()

        fun onCancelClick()
    }
}