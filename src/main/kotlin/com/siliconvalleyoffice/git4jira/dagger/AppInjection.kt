package com.siliconvalleyoffice.git4jira.dagger

import com.google.gson.Gson
import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.services.GitHubService
import com.siliconvalleyoffice.git4jira.services.JsonFilesService
import com.siliconvalleyoffice.git4jira.services.LoginService
import com.siliconvalleyoffice.git4jira.services.RxService
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

    fun plus(git4JiraCredentialsModule: Git4JiraCredentialsModule): Git4JiraCredentialsSubComponent

    fun plus(homeModule: HomeModule): HomeSubComponent

    fun plus(projectProfileModule: ProjectProfileModule): ProjectProfileSubComponent

    fun plus(createProjectModule: CreateProjectModule): CreateProjectSubComponent
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
    fun provideFileService(gson: Gson): Service.JsonFiles = JsonFilesService(gson)

    @Singleton
    @Provides
    fun provideLoginService(gson: Gson): Service.Login = LoginService(gson)

    @Singleton
    @Provides
    fun provideGitHubService(): Service.GitHub = GitHubService()

    @Singleton
    @Provides
    fun provideRxService(): RxService = RxService()
}