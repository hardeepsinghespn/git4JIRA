package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Credentials
import com.siliconvalleyoffice.git4jira.model.Project

interface ProjectProfile {

    interface View {

        fun updateListView()

        fun listViewSelection() : String

        fun defineTabs(project: Project?)
    }

    interface Controller {

        fun projectNames(): List<String>

        fun onAddProjectClick()

        fun onDeleteProjectClick()

        fun onListSelectionChanged(selectedValue: String)
    }
}