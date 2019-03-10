package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.GitTab
import com.siliconvalleyoffice.git4jira.service.Service

class GitTabController(private val gitTabView: GitTab.View,
                       private val jsonFilesService: Service.JsonFiles
): GitTab.Controller {
}