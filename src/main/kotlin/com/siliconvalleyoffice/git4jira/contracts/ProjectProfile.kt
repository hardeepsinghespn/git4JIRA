package com.siliconvalleyoffice.git4jira.contracts

interface ProjectProfile {

    interface View {

    }

    interface Controller {
        fun onAddProjectClick()

        fun onDeleteProjectClick()
    }
}