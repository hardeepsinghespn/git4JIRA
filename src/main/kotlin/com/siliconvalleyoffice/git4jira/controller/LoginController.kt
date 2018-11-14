package loginapp.controllers

import javafx.beans.property.SimpleStringProperty
import com.siliconvalleyoffice.git4jira.model.UserModel
import loginapp.views.LoginView
import tornadofx.*
import view.MainView

class LoginController : Controller() {
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
                find(LoginView::class).replaceWith(MainView::class)
            } else {
                status = json.string("message") ?: "Login failed"
                authenticatedPassword = ""
            }
        }
    }

    fun logout() {
        user.item = null
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginView::class, sizeToScene = true, centerOnScreen = true)
    }

}