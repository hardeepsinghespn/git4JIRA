package com.siliconvalleyoffice.git4jira.contract

import com.siliconvalleyoffice.git4jira.model.Project

interface Home {

    interface View {

        fun updateView()

        fun refreshTabs()

        fun launchLoginView(): Boolean
    }

    interface Controller {

        fun projectNames(): List<String>

        fun lastSelectedProject(): Project?

        fun onEditButtonClick()

        fun onPrintButtonClick()

        fun onLogoutButtonClick()

        fun onGitHubErrorClick()

        fun onJiraErrorClick()

        fun onSlackErrorClick()

        fun onTeamCityClick()

        fun onChoiceBoxSelectionChanged(selectedValue: String)
    }
}