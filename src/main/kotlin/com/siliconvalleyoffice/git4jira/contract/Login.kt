package com.siliconvalleyoffice.git4jira.contract

interface Login {

    interface View {

        fun updateStatus(status: String)

        fun launchHome(): Boolean
    }

    interface Controller {

        fun login(userName: String, password: String)

        fun logout()
    }
}