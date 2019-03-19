package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Project

interface ProjectProfile {

    interface View {

        fun updateListView()

        fun listViewSelection() : String

        fun defineTabs(project: Project?)
    }

    interface Controller {

        fun projectNames(): List<String>

        fun lastSelectedProjectName(): String?

        fun onAddProjectClick()

        fun onEditProjectClick(projectName: String)

        fun onDeleteProjectClick(projectName: String)

        fun onListSelectionChanged(selectedValue: String)
    }
}