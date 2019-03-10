package com.siliconvalleyoffice.git4jira.contract

interface Git4JiraCredentials {

    interface View {

        fun closeView()

        fun encryptionPhrase(): String

        fun encryptionKey(): String

        fun launchHome(): Boolean
    }

    interface Controller {

        fun onValidateClick()
    }
}