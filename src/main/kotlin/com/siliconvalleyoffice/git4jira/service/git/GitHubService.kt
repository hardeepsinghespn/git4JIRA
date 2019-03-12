package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.model.GitAuthorizations
import com.siliconvalleyoffice.git4jira.service.GitService
import io.reactivex.Single
import javax.inject.Inject

class GitHubService(override var name: String, override val logo: String): GitService {

    @Inject
    lateinit var gitRepository: GitRepository

    init {
        Injector.Instance.appComponent.inject(this)
    }

    override fun validateBaseUrl(baseUrl: String) = gitRepository.validateBaseUrl(baseUrl)

    override fun validate(token: String) = gitRepository.validate(token)

    override fun authenticate() = gitRepository.authenticate()

    override fun getUser(username: String) = gitRepository.getUser(username)
}