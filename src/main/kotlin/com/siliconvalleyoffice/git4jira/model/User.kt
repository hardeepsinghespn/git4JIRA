package com.siliconvalleyoffice.git4jira.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User : JsonModel {
    val loginProperty = SimpleStringProperty()
    var login by loginProperty
    val avatarUrlProperty = SimpleStringProperty()
    var avatarUrl by avatarUrlProperty
    val urlProperty = SimpleStringProperty()
    var url by urlProperty
    val htmlUrlProperty = SimpleStringProperty()
    var html_url by htmlUrlProperty
    val reposUrlProperty = SimpleStringProperty()
    var repos_url by reposUrlProperty

    override fun updateModel(json: JsonObject) {
        with (json) {
            login = string("login")
            avatarUrl = string("avatar_url")
            url = string("url")
            html_url = string("html_url")
            repos_url = string("repos_url")
        }
    }
}

class UserModel : ItemViewModel<User>() {
    val name = bind(User::loginProperty)
    val avatarUrl = bind(User::avatarUrlProperty)
    val url = bind(User::urlProperty)
    val htmlUrl = bind(User::htmlUrlProperty)
    val reposUrl = bind(User::reposUrlProperty)
}