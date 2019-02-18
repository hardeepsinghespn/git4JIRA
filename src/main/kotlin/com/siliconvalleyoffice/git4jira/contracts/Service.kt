package com.siliconvalleyoffice.git4jira.contracts

import com.siliconvalleyoffice.git4jira.app.PullRequestState
import com.siliconvalleyoffice.git4jira.models.Configuration
import com.siliconvalleyoffice.git4jira.models.Project
import com.siliconvalleyoffice.git4jira.models.ProjectProfileData
import com.siliconvalleyoffice.git4jira.models.User
import org.eclipse.egit.github.core.PullRequest
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.RepositoryCommit


interface Service {

    interface JsonFiles {

        var configuration: Configuration

        var projectProfileData: ProjectProfileData

        fun validateCredentials(encryptionPhrase: String, encryptionKey: String): Boolean

        fun addProject(project: Project)

        fun removeProject(projectName: String)

        fun editProject(projectName: String)
    }

    interface Login {

        fun login(username: String, password: String): Pair<User?, Error?>

        fun logout()
    }

    interface GitHub {

        fun repositories(): List<Repository>

        fun repository(repo: String): Repository

        fun pullRequests(repo: String, state: PullRequestState? = PullRequestState.ALL): List<PullRequest>

        fun offHourPullRequests(repo: String, state: PullRequestState? = PullRequestState.ALL): List<PullRequest>

        fun onHourPullRequests(repo: String, state: PullRequestState? = PullRequestState.ALL): List<PullRequest>

        fun commits(repo: String): List<RepositoryCommit>

        fun offHourCommits(repo: String): List<RepositoryCommit>

        fun onHourCommits(repo: String): List<RepositoryCommit>
    }
}