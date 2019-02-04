package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.HOME_VIEW
import com.siliconvalleyoffice.git4jira.contracts.Home
import com.siliconvalleyoffice.git4jira.dagger.HomeModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import javafx.scene.Parent
import javafx.scene.layout.AnchorPane
import tornadofx.*
import javax.inject.Inject

class HomeView: View(), Home.View {

    @Inject
    lateinit var homeController: Home.Controller

    override val root: AnchorPane by fxml(HOME_VIEW)

    init {
        Injector.Instance.appComponent.plus(HomeModule(this)).inject(this)
        title = "git4Jira"
    }
}