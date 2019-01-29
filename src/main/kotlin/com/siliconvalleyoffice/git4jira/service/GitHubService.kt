package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.PullRequestState
import com.siliconvalleyoffice.git4jira.contracts.Service
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.CommitService
import org.eclipse.egit.github.core.service.PullRequestService
import org.eclipse.egit.github.core.service.RepositoryService
import java.util.*

class GitHubService(
        var username: String? = "",
        var password: String? = "",
        val token: String? = ""
): Service.GitHub {

    val START_HOUR = 9
    val END_HOUR = 17

    var gitHubClient: GitHubClient
    var repoService: RepositoryService
    var pullRequestService: PullRequestService
    var commitService: CommitService

    init {
        gitHubClient = GitHubClient().setCredentials(this.username, this.password)
        repoService = RepositoryService(gitHubClient)
        commitService = CommitService(gitHubClient)
        pullRequestService = PullRequestService(gitHubClient)
    }

    override fun repositories(): MutableList<Repository> = repoService.getRepositories(username)

    override fun repository(repo: String): Repository = repoService.getRepository(username, repo)

    override fun pullRequests(repo: String, state: PullRequestState?) = pullRequestService.getPullRequests(repository(repo), state?.name?.toLowerCase())

    override fun offHourPullRequests(repo: String, state: PullRequestState?) = pullRequests(repo, state).filter { !isBetweenBusinessHours(it.updatedAt) }

    override fun onHourPullRequests(repo: String, state: PullRequestState?) = pullRequests(repo, state).filter { isBetweenBusinessHours(it.updatedAt) }

    override fun commits(repo: String) = commitService.getCommits(repository(repo))

    override fun offHourCommits(repo: String) = commits(repo).filter { !isBetweenBusinessHours(it.commit.author.date) }

    override fun onHourCommits(repo: String) = commits(repo).filter { isBetweenBusinessHours(it.commit.author.date) }

    private fun isBetweenBusinessHours(commitDateTime: Date): Boolean {
        val hour = Calendar.getInstance().apply { time = commitDateTime }.get(Calendar.HOUR_OF_DAY)
        return hour in (START_HOUR + 1)..(END_HOUR - 1)
    }

}