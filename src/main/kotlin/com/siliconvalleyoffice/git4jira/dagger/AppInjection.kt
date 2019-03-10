package com.siliconvalleyoffice.git4jira.dagger

import com.siliconvalleyoffice.git4jira.Git4JiraApp
import com.siliconvalleyoffice.git4jira.service.git.GitAuthInterceptor
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.service.crendential.LoginService
import com.siliconvalleyoffice.git4jira.service.git.GitHubService
import com.siliconvalleyoffice.git4jira.service.git.GitRepository
import com.siliconvalleyoffice.git4jira.service.json.JsonFileService
import com.siliconvalleyoffice.git4jira.util.GITHUB_API_BASE_URL
import com.squareup.moshi.Moshi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    fun inject(git4JiraApp: Git4JiraApp)

    fun inject(gitHubService: GitHubService)

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
    fun provideGitAuthInterceptor(): GitAuthInterceptor = GitAuthInterceptor()

    @Singleton
    @Provides
    fun provideGitRetrofit(gitAuthInterceptor: GitAuthInterceptor): GitRepository {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(gitAuthInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(GITHUB_API_BASE_URL)
                .client(okHttpClient)

        return retrofit.build().create(GitRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideFileService(moshi: Moshi, gitAuthInterceptor: GitAuthInterceptor): Service.JsonFiles = JsonFileService(moshi, gitAuthInterceptor)

    @Singleton
    @Provides
    fun provideLoginService(moshi: Moshi): Service.Login = LoginService(moshi)
}