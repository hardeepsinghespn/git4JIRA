package com.siliconvalleyoffice.git4jira.contracts

import javax.print.attribute.standard.JobOriginatingUserName

interface Login {

    interface View {
        fun updateStatus(status: String)
    }

    interface Controller {

        fun login(userName: String, password: String)

        fun logout()
    }
}