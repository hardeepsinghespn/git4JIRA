package com.siliconvalleyoffice.git4jira.controller

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.model.User
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import javafx.application.Platform
import tornadofx.*
import tornadofx.FX.Companion.primaryStage
import javax.json.JsonObject

class LoginController(val loginView: LoginView, val loginService: Service.Login, val gson: Gson): Login.Controller {
    val api: Rest = Rest()
    var user: User? = null

    init {
        api.baseURI = "https://api.github.com/"
    }

    override fun login(username: String, password: String) {
        api.setBasicAuth(username, password)
        val response = api.get("user")
        val json: JsonObject = response.one()
        if (response.ok()) {
            user = gson.fromJson(json.toString(), User::class.java)
            System.out.println("User: " + user.toString())
            Platform.runLater { find(LoginView::class).replaceWith(HomeView::class, sizeToScene = true, centerOnScreen = true) }
        } else {
            loginView.updateStatus(json.string("message") ?: "Login failed")
        }
    }

    override fun logout() {
        user = null
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginView::class, sizeToScene = true, centerOnScreen = true)
    }
}