package com.siliconvalleyoffice.git4jira.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class Repo : JsonModel {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty
    val gitUrlProperty = SimpleStringProperty()
    var git_url by gitUrlProperty
    val cloneUrlProperty = SimpleStringProperty()
    var clone_url by cloneUrlProperty
    val defaultBranchProperty = SimpleStringProperty()
    var defaultBranch by defaultBranchProperty

    override fun updateModel(json: JsonObject) {
        with (json) {
            name = string("name")
            git_url = string("git_url")
            clone_url = string("clone_url")
            defaultBranch = string("default_branch")
        }
    }
}

class RepoModel : ItemViewModel<Repo>() {
    val name = bind(Repo::nameProperty)
    val gitUrl = bind(Repo::gitUrlProperty)
    val cloneUrl = bind(Repo::cloneUrlProperty)
    val defaultBranch = bind(Repo::defaultBranchProperty)
}