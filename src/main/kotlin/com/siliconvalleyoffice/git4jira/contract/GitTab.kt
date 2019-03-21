package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.GitServiceConfig
import com.siliconvalleyoffice.git4jira.model.Project
import com.siliconvalleyoffice.git4jira.service.GitType
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField

interface GitTab {

    interface View {

        fun projectName(): String?

        fun updateValidationIcon(gitServiceConfig: GitServiceConfig?, valid: Boolean)

        fun updateBaseUrl(gitType: GitType?)

        fun updateAccountName(name: String)

        fun disableValidationButton(disable: Boolean)
    }

    interface Controller {

        fun gitProviderItems(): List<String>

        fun project(): Project?

        fun onTypeSelectionChanged(newValue: String)

        fun onValidationClicked(provider: String, gitTypeName: String, baseUrl: String, accountName:String, password: String)
    }
}