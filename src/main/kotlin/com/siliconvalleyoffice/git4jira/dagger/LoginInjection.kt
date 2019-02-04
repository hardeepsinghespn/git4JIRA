package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.controller.LoginController
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
    fun providesLoginController(): Login.Controller = LoginController(loginView)
}