package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Project

interface GitTab {

    interface View {

        fun projectName(): String?

        fun updateBaseUrlValidationIcon(valid: Boolean)

        fun updateCredentialsValidationIcon(valid: Boolean)
    }

    interface Controller {

        fun gitProviderItems(): List<String>

        fun project(): Project?

        fun onBaseUrlValidationClicked(provider: String, baseUrl: String)

        fun onCredentialsValidationClicked(accountName: String, password: String)
    }
}