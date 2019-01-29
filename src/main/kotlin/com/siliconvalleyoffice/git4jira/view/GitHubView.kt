package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contracts.GitHub
import com.siliconvalleyoffice.git4jira.dagger.GitHubModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import javafx.scene.layout.VBox
import tornadofx.*
import javax.inject.Inject

class GitHubView: View(), GitHub.View {

    @Inject
    lateinit var githubController: GitHub.Controller

    override val root = VBox()

    init {
        Injector.Instance.appComponent.plus(GitHubModule(this)).inject(this)
    }
}