package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.view.LoginView

class LoginController(loginView: LoginView): Login.Controller {

    override fun login(username: String, password: String) {/* TODO */ }

    override fun login(token: String) {/* TODO */ }

    override fun logout() {/* TODO */ }
}