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

    val api: Rest = Rest()
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    var originForks = FXCollections.observableArrayList(
            "Option 1",
            "Option 2",
            "Option 3"
    )

    init {
        api.baseURI = "http://api.github.com/users/"
    }

    fun getDeveloperForks(developerName : String) {
        runLater { status = "" }
        api.setBasicAuth(LoginController.instance.user.name.getValue(), LoginController.instance.authenticatedPassword)
        val path = developerName + "/repos"
        val response = api.get(path) // /users/:username/repos
        val json = response.list()
        runLater {
            if (response.ok()) {
                val theResponse = json.toPrettyString()
            } else {
//                status = json.string("message") ?: "Login failed"
//                authenticatedPassword = ""
            }
        }
    }
}