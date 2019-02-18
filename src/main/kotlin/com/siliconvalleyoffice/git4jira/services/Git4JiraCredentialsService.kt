package com.siliconvalleyoffice.git4jira.services

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.app.GITHUB_BASE_URL
import com.siliconvalleyoffice.git4jira.app.MESSAGE
import com.siliconvalleyoffice.git4jira.app.USER
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.models.User
import tornadofx.*
import javax.json.JsonObject


class Git4JiraCredentialsService(val gson: Gson): Service.Login {
    val api: Rest = Rest()
    var user: User? = null

    init {
        api.baseURI = GITHUB_BASE_URL
    }

    override fun login(username: String, password: String): Pair<User?, Error?> {
        api.setBasicAuth(username, password)
        val response = api.get(USER)
        val json: JsonObject = response.one()
        if (response.ok()) {
            user = gson.fromJson(json.toString(), User::class.java)
            return Pair(user, null)
        } else {
            return Pair(null, Error(json.string(MESSAGE)))
        }
    }

    override fun logout() {
        user = null
    }
}