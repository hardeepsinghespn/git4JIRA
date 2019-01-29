package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.contracts.Service
import com.siliconvalleyoffice.git4jira.service.GitHubService
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

    fun plus(gitHubModule: GitHubModule): GitHubComponent
}

/**
 * Application Module to provide global dependencies
 */
@Module
class AppModule {

    @Provides
    fun provideGitHubService(): Service.GitHub = GitHubService()
}