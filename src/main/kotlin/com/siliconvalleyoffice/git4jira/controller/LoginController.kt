package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.MAIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.MAIN_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.model.UserModel
import com.siliconvalleyoffice.git4jira.service.GitHubService
import com.siliconvalleyoffice.git4jira.view.LoginView
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import view.MainView

class LoginController private constructor() : Controller() {
    private object Holder { val INSTANCE = LoginController() }
    companion object {
        val instance: LoginController by lazy { Holder.INSTANCE }
    }

    val statusProperty = SimpleStringProperty("")
    var status by statusProperty
    val api: Rest = Rest()
    val user: UserModel by inject()
    var authenticatedPassword = ""
    var authToken = ""
    var gitHubService = GitHubService("", "")

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun login(username: String, password: String) {
        api.setBasicAuth(username, password)
        val response = api.get("user")
        val json = response.one()
        if (response.ok()) {
            user.item = json.toModel()
            authenticatedPassword = password
//            authToken = retrieveOauthToken(username, password)
            gitHubService = GitHubService(username, password)
            gitHubService.pullForks()
            setMainWindowStageDimensions()
            find(LoginView::class).replaceWith(MainView::class)
        } else {
            status = json.string("message") ?: "Login failed"
            authenticatedPassword = ""
            gitHubService = GitHubService("", "")
        }
    }

    private fun retrieveOauthToken(username: String, password: String) : String {
        val theAuthToken : String
        api.setBasicAuth(username, password)
        val response = api.get("authorizations")
        val json = response.one()
        if (response.ok()) {
            theAuthToken = json.getString("hashed_token")
        } else {
            theAuthToken = ""
            status = json.string("message") ?: "Auth Token failed"
        }
        return theAuthToken
    }

    fun logout() {
        user.item = null
        authenticatedPassword = ""
        authToken = ""
        setLoginStageDimensions()
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginView::class, sizeToScene = true, centerOnScreen = true)
    }

    private fun setLoginStageDimensions() {
        primaryStage.maxWidth = LOGIN_VIEW_WIDTH
        primaryStage.maxHeight = LOGIN_VIEW_HEIGHT
        primaryStage.minWidth = LOGIN_VIEW_WIDTH
        primaryStage.minHeight = LOGIN_VIEW_HEIGHT
    }

    private fun setMainWindowStageDimensions() {
        primaryStage.minWidth = MAIN_VIEW_WIDTH
        primaryStage.minHeight = MAIN_VIEW_HEIGHT
        primaryStage.maxWidth = MAIN_VIEW_WIDTH
        primaryStage.maxHeight = MAIN_VIEW_HEIGHT
    }
}