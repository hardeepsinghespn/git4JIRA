package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Credentials

interface ProjectProfile {

    interface View {

        fun updateListView()

        fun listViewSelection() : String

        fun defineTabs(credentials: List<Credentials>?)
    }

    interface Controller {

        fun getProjectNames(): List<String>

        fun onAddProjectClick()

        fun onDeleteProjectClick()

        fun onListSelectionChanged(selectedValue: String)
    }
}