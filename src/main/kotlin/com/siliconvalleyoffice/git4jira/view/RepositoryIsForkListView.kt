package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git.RepositoryConverter
import com.siliconvalleyoffice.git4jira.controller.LoginControllerOld
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.eclipse.egit.github.core.Repository
import tornadofx.*

class RepositoryIsForkListView : View() {
    override val root = GridPane()
    val selectedClonedOrigin = SimpleObjectProperty<Repository>()
    val forkListObservable = LoginControllerOld.INSTANCE.gitHubController.getForkListObservable()
    val theComboBox = ComboBox<Repository>(forkListObservable)
    val tableView = TableView<Repository>(forkListObservable)

    init {
        title = "Origin Forks"
        theComboBox.converterProperty().set(RepositoryConverter())
        root.add(theComboBox)
        tableView.editableProperty().set(false)
    }

    fun createNameColumn() : TableColumn<Repository,String> {
        var name = TableColumn<Repository,String>("Name")
//        name.setCellValueFactory(Callback<TableColumn.CellDataFeatures<Repository, String>, ObservableValue<String>>() {
//            fun call(p : TableColumn.CellDataFeatures<Repository, ObservableValue<String>>) {
//                return p.getValue().name();
//            }
//        })


    /*
    TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("First Name");
    * firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
        *     public ObservableValue<String> call(CellDataFeatures<Person, String> p) {
        *         // p.getValue() returns the Person INSTANCE for a particular TableView row
        *         return p.getValue().firstNameProperty();
        *     }
        *  });
    * }
* tableView.getColumns().add(firstNameCol);}</pre>
*/
    return name
}
}