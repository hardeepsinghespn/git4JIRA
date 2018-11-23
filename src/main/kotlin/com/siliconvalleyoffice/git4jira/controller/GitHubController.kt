package com.siliconvalleyoffice.git4jira.controller

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.*

/*
    1 instance is created for each login session
 */
class GitHubController constructor(user: String, password: String) {
    private val logger = LoggerFactory.getLogger(GitHubController::class.toString())
    val userName = user
    private val client = GitHubClient().setCredentials(user, password)
    private val repoService = RepositoryService(client)
//    private val commitService = CommitService(client)
//    private val commits = ArrayList<Commit>()
    private var forkList: ArrayList<Repository> = ArrayList<Repository>()
        private set
    private var repositoryIsForkedObservableList = FXCollections.observableList<Repository>(forkList)
        private set

    fun getForkList() : List<Repository> {
        val repoList : List<Repository>
        forkList.clear()
        try {
            repoList = repoService.repositories
            for (repo in repoList) {
                if(repo.isFork ) {
                    forkList.add(repo)
                }
            }
        } catch (e: IOException) {
            logger.error("Could not pull Forks from GitHub for $userName.")
            e.printStackTrace()
        }
        return forkList
    }

//    fun getCommitsByTag(tag: Tag?): List<Commit> {
//        val matches = ArrayList<Commit>()
//
//        if (this.commits.size == 0) {
//            this.getForkList()
//        }
//
//        for (commit in this.commits) {
////            if (commit != null && tag != null && commit!!.getMessage() != null && StringUtils.containsIgnoreCase(commit!!.getMessage(), tag!!.getName())) {
////                //Taking advantage of reference to add tag
////                commit!!.getTags().add(tag)
//            matches.add(commit)
////            }
//        }
//        return matches
//    }

    fun getForkListProperty(): SimpleObjectProperty<List<Repository>> {
        return getForkListProperty()
    }

    fun getForkListMock(): List<String>? {
        return listOf(
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4"
        )
    }

    fun getForkListObservable(): ObservableList<Repository>? {
        return repositoryIsForkedObservableList
    }

}
