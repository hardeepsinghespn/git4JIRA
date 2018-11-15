package com.siliconvalleyoffice.git4jira.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User : JsonModel {
    val loginProperty = SimpleStringProperty()  // flywheelms
    var login by loginProperty
    val avatarUrlProperty = SimpleStringProperty()  // https://avatars2.githubusercontent.com/u/10121039?v=4
    var avatarUrl by avatarUrlProperty
    val userUrlProperty = SimpleStringProperty()  // https://api.github.com/users/flywheelms
    var userUrl by userUrlProperty
    val htmlUrlProperty = SimpleStringProperty()  // https://github.com/flywheelms
    var html_url by htmlUrlProperty
    val reposUrlProperty = SimpleStringProperty()  // https://api.github.com/users/flywheelms/repos
    var repos_url by reposUrlProperty

    override fun updateModel(json: JsonObject) {
        with (json) {
            login = string("login")
            avatarUrl = string("avatar_url")
            userUrl = string("url")
            html_url = string("html_url")
            repos_url = string("repos_url")
        }
    }
}

class UserModel : ItemViewModel<User>() {
    val name = bind(User::loginProperty)
    val avatarUrl = bind(User::avatarUrlProperty)
    val url = bind(User::userUrlProperty)
    val htmlUrl = bind(User::htmlUrlProperty)
    val reposUrl = bind(User::reposUrlProperty)
}