package com.siliconvalleyoffice.git4jira.service

import org.eclipse.egit.github.core.Commit
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.RepositoryId
import org.eclipse.egit.github.core.Tag
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.CommitService
import org.eclipse.egit.github.core.service.RepositoryService
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.*


class GitHubService constructor(user: String, password: String) {
    private val logger = LoggerFactory.getLogger(GitHubService::class.toString())
    val userName = user
    val client = GitHubClient().setCredentials(user, password)
    private val repoService = RepositoryService(client)
    private val commitService = CommitService(client)
    private val commits = ArrayList<Commit>()

    fun pullForks() : List<Repository> {
        var repoList: List<Repository>
        try {
            repoList = repoService.repositories
            for (repo in repoList) {
                val repoId = RepositoryId(userName, repo.name)
            }
        } catch (e: IOException) {
            logger.error("Could not pull Forks from GitHub for $userName.")
            e.printStackTrace()
            repoList = emptyList()
        }
        return repoList
    }

    fun getCommitsByTag(tag: Tag?): List<Commit> {
        val matches = ArrayList<Commit>()

        if (this.commits.size == 0) {
            this.pullForks()
        }

        for (commit in this.commits) {
//            if (commit != null && tag != null && commit!!.getMessage() != null && StringUtils.containsIgnoreCase(commit!!.getMessage(), tag!!.getName())) {
//                //Taking advantage of reference to add tag
//                commit!!.getTags().add(tag)
                matches.add(commit)
//            }
        }
        return matches
    }


}