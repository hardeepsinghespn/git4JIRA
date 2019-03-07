package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.service.GitService
import javax.inject.Inject

class GitHubService(override var name: String, override val logo: String): GitService {

    @Inject
    lateinit var gitRepository: GitRepository

    init {
        Injector.Instance.appComponent.inject(this)
    }

    override fun getUser(username: String) = gitRepository.getUser(username)

    override fun authenticate() = gitRepository.authenticate()
}