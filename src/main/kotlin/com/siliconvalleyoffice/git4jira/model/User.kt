package com.siliconvalleyoffice.git4jira.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User : JsonModel {
    val loginProperty = SimpleStringProperty()
    var login by loginProperty
    val avatarUrlProperty = SimpleStringProperty()
    var avatarUrl by avatarUrlProperty
    val userUrlProperty = SimpleStringProperty()
    var userUrl by userUrlProperty
    val htmlUrlProperty = SimpleStringProperty()
    var html_url by htmlUrlProperty
    val reposUrlProperty = SimpleStringProperty()
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


//Sample User Response
/**
{
"login": "hardeepsingh07",
"id": 6466575,
"node_id": "MDQ6VXNlcjY0NjY1NzU=",
"avatar_url": "https://avatars0.githubusercontent.com/u/6466575?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/hardeepsingh07",
"html_url": "https://github.com/hardeepsingh07",
"followers_url": "https://api.github.com/users/hardeepsingh07/followers",
"following_url": "https://api.github.com/users/hardeepsingh07/following{/other_user}",
"gists_url": "https://api.github.com/users/hardeepsingh07/gists{/gist_id}",
"starred_url": "https://api.github.com/users/hardeepsingh07/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/hardeepsingh07/subscriptions",
"organizations_url": "https://api.github.com/users/hardeepsingh07/orgs",
"repos_url": "https://api.github.com/users/hardeepsingh07/repos",
"events_url": "https://api.github.com/users/hardeepsingh07/events{/privacy}",
"received_events_url": "https://api.github.com/users/hardeepsingh07/received_events",
"type": "User",
"site_admin": false,
"name": "Hardeep Singh",
"company": null,
"blog": "",
"location": null,
"email": "hardeep07@yahoo.com",
"hireable": true,
"bio": "Software Engineer",
"public_repos": 23,
"public_gists": 0,
"followers": 10,
"following": 8,
"created_at": "2014-01-22T01:49:28Z",
"updated_at": "2019-02-05T19:20:00Z",
"private_gists": 0,
"total_private_repos": 2,
"owned_private_repos": 2,
"disk_usage": 48692,
"collaborators": 0,
"two_factor_authentication": false,
"plan": {
"name": "pro",
"space": 976562499,
"collaborators": 0,
"private_repos": 9999
}
}
 */