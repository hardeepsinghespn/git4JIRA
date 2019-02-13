package com.siliconvalleyoffice.git4jira.dagger

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.service.GitHubService
import com.siliconvalleyoffice.git4jira.service.LoginService
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application Component to provide global dependency module
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun plus(loginModule: LoginModule): LoginSubComponent

    fun plus(homeModule: HomeModule): HomeSubComponent

    fun plus(projectProfileModule: ProjectProfileModule): ProjectProfileSubComponent
}

/**
 * Application Module to provide global dependencies
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideLoginService(gson: Gson): Service.Login = LoginService(gson)

    @Singleton
    @Provides
    fun provideGitHubService(): Service.GitHub = GitHubService()
}