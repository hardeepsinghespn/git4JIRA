package com.siliconvalleyoffice.git4jira.contracts

interface Login {

    interface View {
        fun updateStatus(status: String)
    }

    interface Controller {

        fun login(username: String, password: String)

        fun login(token: String)

        fun logout()
    }
}