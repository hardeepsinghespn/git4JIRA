package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.model.Repo
import tornadofx.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javax.json.JsonArray


class GitHubController private constructor() : Controller() {
    private object Holder { val INSTANCE = GitHubController() }
    companion object {
        val instance: GitHubController by lazy { Holder.INSTANCE }
    }

    val api: Rest = Rest()

    var originForks = FXCollections.observableArrayList(
            "Option 1",
            "Option 2",
            "Option 3"
    )

    init {
        api.baseURI = "http://api.github.com/users/"
    }

    fun getDeveloperForks(developerName : String) : ObservableList<Repo> {
        val originForkList : ObservableList<Repo>
        api.setBasicAuth(LoginController.instance.user.name.getValue(), LoginController.instance.authenticatedPassword)
        val path = developerName + "/repos"
        val response = api.get(path)
        val jsonArray = response.list()
        if (response.ok()) {
            originForkList = parseJsonArray(jsonArray)
        } else {
            originForkList = FXCollections.observableArrayList()
        }
        return originForkList
    }

    private fun parseJsonArray(jsonArray: JsonArray): ObservableList<Repo> {
        return FXCollections.observableArrayList()
    }
}