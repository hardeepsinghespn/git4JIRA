package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.LoginModule
import javafx.scene.layout.VBox
import tornadofx.*
import javax.inject.Inject

class LoginView: View(), Login.View {

    @Inject
    lateinit var loginController: Login.Controller

    override val root = VBox()

    init {
        Injector.Instance.appComponent.plus(LoginModule(this)).inject(this)
        title = "Github Login"

        with(root) {
            text {
                text = loginController.toString()
            }
        }
    }

    override fun updateStatus(status: String) {
        //Todo
    }
}