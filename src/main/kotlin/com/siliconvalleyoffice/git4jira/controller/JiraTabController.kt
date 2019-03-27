package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.JiraTab
import com.siliconvalleyoffice.git4jira.service.Service

class JiraTabController(private val jiraTabView: JiraTab.View,
                        private val jsonFilesService: Service.JsonFiles
) : JiraTab.Controller {
}