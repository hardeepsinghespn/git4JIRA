package com.siliconvalleyoffice.git4jira.contracts

interface CreateProject {

    interface View {

    }

    interface Controller {

        fun onBrowseClick()

        fun onCreateClick()

        fun onCancelClick()
    }
}