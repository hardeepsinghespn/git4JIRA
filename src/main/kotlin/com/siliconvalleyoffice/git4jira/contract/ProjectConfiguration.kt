package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Project

interface ProjectConfiguration {

    interface View {

        fun updateListView()

        fun listViewSelection() : String

        fun defineTabs(project: Project?)

        fun updateGitTabIcon(icon: String?)

        fun updateJiraTabIcon(icon: String?)

        fun updateCommunicationTabIcon(icon: String?)

        fun updateContinuousIntegrationTabIcon(icon: String?)
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