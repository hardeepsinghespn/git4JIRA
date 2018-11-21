package com.siliconvalleyoffice.git4jira.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class Repo : JsonModel {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty
    val descriptionProperty = SimpleStringProperty()
    var description by descriptionProperty
    val forkProperty = SimpleStringProperty()
    var fork by forkProperty
    val cloneUrlProperty = SimpleStringProperty()
    var clone_url by cloneUrlProperty
    val branchesUrlProperty = SimpleStringProperty()
    var branchesUrl by branchesUrlProperty
    val pullsUrlProperty = SimpleStringProperty()
    var pullsUrl by pullsUrlProperty
    val mergesUrlProperty = SimpleStringProperty()
    var mergesUrl by mergesUrlProperty
    val defaultBranchProperty = SimpleStringProperty()
    var defaultBranch by defaultBranchProperty

    override fun updateModel(json: JsonObject) {
        with (json) {
            name = string("name")
            description = string("description")
            fork = string("fork")
            clone_url = string("clone_url")
            branchesUrl = string("branches_url")
            pullsUrl = string("pulls_url")
            mergesUrl = string("merges_url")
            defaultBranch = string("default_branch")
        }
    }
}

class RepoModel : ItemViewModel<Repo>() {
    val name = bind(Repo::nameProperty)
    val description = bind(Repo::descriptionProperty)
    val fork = bind(Repo::forkProperty)
    val cloneUrl = bind(Repo::cloneUrlProperty)
    val branchesUrl = bind(Repo::branchesUrlProperty)
    val pullsUrl = bind(Repo::pullsUrlProperty)
    val mergesUrl = bind(Repo::mergesUrlProperty)
    val defaultBranch = bind(Repo::defaultBranchProperty)
}