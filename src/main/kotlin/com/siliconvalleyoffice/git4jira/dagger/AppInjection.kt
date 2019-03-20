package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.Git4JiraApp
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.crendential.LoginService
import com.siliconvalleyoffice.git4jira.service.git.GitHubService
import com.siliconvalleyoffice.git4jira.service.json.JsonFileService
import com.squareup.moshi.Moshi
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Application Component to provide global dependency module
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    /**
     * Direct References
     */
    fun retrofitBuilder(): Retrofit.Builder


    /**
     * Injections
     */
    fun inject(git4JiraApp: Git4JiraApp)

    fun inject(gitHubService: GitHubService)

    /**
     * Sub-Components
     */
    fun plus(loginModule: LoginModule): LoginSubComponent

    fun plus(git4JiraCredentialsModule: Git4JiraCredentialsModule): Git4JiraCredentialsSubComponent

    fun plus(homeModule: HomeModule): HomeSubComponent

    fun plus(projectProfileModule: ProjectProfileModule): ProjectProfileSubComponent

    fun plus(createProjectModule: CreateProjectModule): CreateProjectSubComponent

    fun plus(gitTabModule: GitTabModule): TabSubComponent
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
    fun provideMoshiConvertorFactory() = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideRetrofitBuilder(moshiConverterFactory: MoshiConverterFactory,
                               rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) =
            Retrofit.Builder()
                    .addConverterFactory(moshiConverterFactory)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)

    @Singleton
    @Provides
    fun provideFileService(moshi: Moshi): Service.JsonFiles = JsonFileService(moshi)

    @Singleton
    @Provides
    fun provideLoginService(moshi: Moshi): Service.Login = LoginService(moshi)
}