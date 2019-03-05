package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contract.Home
import com.siliconvalleyoffice.git4jira.controller.HomeController
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.HomeView
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Home Sub-component to provide Home dependency module
 */
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(homeView: HomeView)
}

/**
 * Home Module to provide Home dependencies
 */
@Module
class HomeModule(private val homeView: HomeView) {

    @Provides
    fun provideHomeController(loginService: Service.Login, jsonFileService: Service.JsonFiles): Home.Controller = HomeController(homeView, loginService, jsonFileService)
}