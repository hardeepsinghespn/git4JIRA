package com.siliconvalleyoffice.git4jira.dagger

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.controller.LoginController
import com.siliconvalleyoffice.git4jira.service.LoginService
import com.siliconvalleyoffice.git4jira.view.LoginView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Login Sub-component to provide Login dependency module
 */
@Subcomponent(modules = [LoginModule::class])
interface LoginSubComponent {
    fun inject(loginView: LoginView)
}

/**
 * Login Module to provide Login dependencies
 */
@Module
class LoginModule(private val loginView: LoginView) {

    @Provides
    fun providesLoginController(loginService: Service.Login, gson: Gson): Login.Controller = LoginController(loginView, loginService, gson)
}