package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import com.siliconvalleyoffice.git4jira.service.GitService
import com.siliconvalleyoffice.git4jira.util.GITHUB_API_BASE_URL
import com.siliconvalleyoffice.git4jira.util.GITHUB_PUBLIC_BASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

class GitHubService(var requestInfo: RequestInfo?): GitService {

    var gitHubRetrofitBuilder: Retrofit.Builder = Injector.Instance.appComponent.retrofitBuilder()
    private val gitHubRepository: GitHubRepository

    init {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(GitAuthInterceptor(requestInfo?.username ?: EMPTY, requestInfo?.password ?: EMPTY))
                .build()

        val retrofit = gitHubRetrofitBuilder
                .baseUrl(requestInfo?.apiUrl() ?: GITHUB_PUBLIC_BASE_URL)
                .client(okHttpClient)
                .build()

        gitHubRepository = retrofit.create(GitHubRepository::class.java)
    }

    override fun validate(baseUrl: String, token: String) = gitHubRepository.validate(baseUrl, token)

    override fun authenticate() = gitHubRepository.authenticate()

    override fun getUser(username: String) = gitHubRepository.getUser(username)
}