package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.MAIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.MAIN_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.model.UserModel
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
    var gitHubController = GitHubController("", "")
        private set(value) {
            field = value
        }

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
            gitHubController = GitHubController(username, password)
            gitHubController.getForkList()
            setMainWindowStageDimensions()
            find(LoginView::class).replaceWith(MainView::class)
        } else {
            status = json.string("message") ?: "Login failed"
            authenticatedPassword = ""
            gitHubController = GitHubController("", "")
        }
    }

    fun logout() {
        user.item = null
        authenticatedPassword = ""
        gitHubController = GitHubController("", "")
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