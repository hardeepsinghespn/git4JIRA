package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git.RepositoryConverter
import com.siliconvalleyoffice.git4jira.controller.LoginController
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ComboBox
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.eclipse.egit.github.core.Repository
import tornadofx.*

class RepositoryIsForkListView : View() {
    override val root = GridPane()
    val selectedClonedOrigin = SimpleObjectProperty<Repository>()
    val theComboBox = ComboBox<Repository>(LoginController.instance.gitHubController.getForkListObservable())
    val tableView = TableView<Repository>()

    init {
        theComboBox.converterProperty().set(RepositoryConverter())
        root.add(theComboBox)
//        root.center.setStyle("-fx-background-color: gray");
    }
}