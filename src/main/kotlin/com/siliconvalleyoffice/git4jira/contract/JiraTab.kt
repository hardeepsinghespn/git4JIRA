package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.model.ProjectManagementServiceConfig

interface JiraTab {

    interface View {

        fun projectName(): String?

        fun updateValidationIcon(projectManagementServiceConfig: ProjectManagementServiceConfig?, valid: Boolean)

        fun updateBoardValidationIcon(valid: Boolean)

        fun updateBoardChoiceBox(boardList: List<String>?)
    }

    interface Controller {

        fun project(): Project?

        fun onValidationButtonClick(baseUrl: String, accountName: String, password: String)

        fun onBoardSearchClick(projectName: String)

        fun onBoardSelectionChanged(newValue: String)
    }
}