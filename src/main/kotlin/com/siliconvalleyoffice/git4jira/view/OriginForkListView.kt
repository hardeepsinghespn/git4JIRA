package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.controller.LoginController
import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import org.eclipse.egit.github.core.Repository
import tornadofx.*

class OriginForkListView : View() {

    override val root = GridPane()

    val mapTableContent = mapOf(Pair("item 1", 5), Pair("item 2", 10), Pair("item 3", 6))
    val forkList = LoginController.instance.gitHubController.getForkList()

    init {
//        with (root) {
//            row {
//                vbox {
//                    label("Forked Repositories")
//                    tableview(forkList) {
//                        readonlyColumn("Name", Repository::getName)
//                        readonlyColumn("Count", Map.Entry<String, Int>::value)
//                        resizeColumnsToFitContent()
//                    }
//                }
//            }
//        }
    }
}