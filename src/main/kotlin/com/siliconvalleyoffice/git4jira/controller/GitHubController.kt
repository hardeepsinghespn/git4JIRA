package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.view.LoginView
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javafx.collections.FXCollections
import view.MainView


class GitHubController private constructor() : Controller() {
    private object Holder { val INSTANCE = GitHubController() }
    companion object {
        val instance: GitHubController by lazy { Holder.INSTANCE }
    }

    val api: Rest by inject()
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    var originForks = FXCollections.observableArrayList(
            "Option 1",
            "Option 2",
            "Option 3"
    )

    init {
        api.baseURI = "https://api.github.com/users/"
    }

    fun getDeveloperForks(developerName : String) {
        runLater { status = "" }
        val response = api.get(developerName + "/repos")
        val json = response.toString()
        runLater {
            if (response.ok()) {
                val theResponse = json
//                user.item = json.toModel()
//                authenticatedPassword = password
//                setMainWindowStageDimensions()
//                find(LoginView::class).replaceWith(MainView::class)
//                GitHubController.instance.getDeveloperForks(user.name.getValue())
            } else {
//                status = json.string("message") ?: "Login failed"
//                authenticatedPassword = ""
            }
        }
    }
}