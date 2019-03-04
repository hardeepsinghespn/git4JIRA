package com.siliconvalleyoffice.git4jira.contract

interface Home {

    interface View {

    }

    interface Controller {

        fun projectNames(): List<String>

        fun onEditButtonClick()

        fun onPrintButtonClick()

        fun onLogoutButtonClick()

        fun onGitHubErrorClick()

        fun onJiraErrorClick()

        fun onSlackErrorClick()

        fun onTeamCityClick()
    }
}