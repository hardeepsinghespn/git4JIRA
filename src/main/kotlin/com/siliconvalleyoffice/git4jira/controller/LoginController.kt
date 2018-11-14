package loginapp.controllers

import javafx.beans.property.SimpleStringProperty
import loginapp.models.UserModel
import loginapp.views.LoginView
import tornadofx.*
import view.MainView

class LoginController : Controller() {
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    val api: Rest by inject()
    val user: UserModel by inject()

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
                find(LoginView::class).replaceWith(MainView::class, sizeToScene = true, centerOnScreen = true)
            } else {
                status = json.string("message") ?: "Login failed"
            }
        }
    }

    fun logout() {
        user.item = null
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginView::class, sizeToScene = true, centerOnScreen = true)
    }

}