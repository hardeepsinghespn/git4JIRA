package com.siliconvalleyoffice.git4jira.contracts

interface Login {

    interface View {
        fun updateStatus(status: String)
    }

    interface Controller {

        fun login(userName: String, password: String)

        fun logout()
    }
}