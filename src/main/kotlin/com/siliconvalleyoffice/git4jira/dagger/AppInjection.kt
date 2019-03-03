package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.crendential.LoginService
import com.siliconvalleyoffice.git4jira.service.json.JsonFileService
import com.squareup.moshi.Moshi
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
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideFileService(moshi: Moshi): Service.JsonFiles = JsonFileService(moshi)

    @Singleton
    @Provides
    fun provideLoginService(moshi: Moshi): Service.Login = LoginService(moshi)
}