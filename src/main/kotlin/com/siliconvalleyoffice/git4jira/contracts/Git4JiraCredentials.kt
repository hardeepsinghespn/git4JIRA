package com.siliconvalleyoffice.git4jira.contracts

import java.io.File

interface Git4JiraCredentials {

    interface View {

        fun closeView()

        fun encryptionPhrase(): String

        fun encryptionKey(): String
    }

    interface Controller {

        fun onValidateClick()
    }
}