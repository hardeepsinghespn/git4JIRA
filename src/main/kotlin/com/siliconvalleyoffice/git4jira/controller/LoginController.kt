package loginapp.controllers

import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.MAIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.MAIN_VIEW_WIDTH
import javafx.beans.property.SimpleStringProperty
import com.siliconvalleyoffice.git4jira.model.UserModel
import javafx.stage.Stage
import loginapp.views.LoginView
import tornadofx.*
import view.MainView

class LoginController private constructor() : Controller() {
    private object Holder { val INSTANCE = LoginController() }
    companion object {
        val instance: LoginController by lazy { Holder.INSTANCE }
    }

    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    val api: Rest by inject()
    val user: UserModel by inject()
    var authenticatedPassword = ""

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun login(username: String, password: String) {
        runLater { status = "" }
        api.setBasicAuth(username, password)
        val response = api.get("user")
        val json = response.one()
        runLater {
            if (response.ok()) {
                user.item = json.toModel()
                authenticatedPassword = password
                setMainWindowStageDimensions()
                find(LoginView::class).replaceWith(MainView::class)
            } else {
                status = json.string("message") ?: "Login failed"
                authenticatedPassword = ""
            }
        }
    }

    fun logout() {
        user.item = null
        authenticatedPassword = ""
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