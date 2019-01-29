package view

import com.siliconvalleyoffice.git4jira.app.APP_NAME
import com.siliconvalleyoffice.git4jira.controller.LoginControllerOld
import com.siliconvalleyoffice.git4jira.view.ClonedOriginView
import com.siliconvalleyoffice.git4jira.view.LoginView
import com.siliconvalleyoffice.git4jira.view.RepositoryIsForkListView
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()
    private val userView: ClonedOriginView by inject()
    private val originRepoListView: RepositoryIsForkListView by inject()

    init {
        title = APP_NAME
        createMenuBar()
        root.center = splitpane {
            orientation = Orientation.VERTICAL
            this += userView
            this += originRepoListView
        }
    }

    fun createMenuBar() {

        val menuBar = MenuBar()
        val mainMenu = Menu("Menu")

        val newRepository = MenuItem("New Repository")

        val signOut = MenuItem("Sign Out")
        signOut.accelerator = KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN)
        signOut.setOnAction(object : EventHandler<ActionEvent> {
            override fun handle(e: ActionEvent) {
                LoginControllerOld.INSTANCE.logout()
                find(MainView::class).replaceWith(LoginView::class)
            }
        });

        val exitCmd = MenuItem("Quit)")
        exitCmd.accelerator = KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        exitCmd.setOnAction(object : EventHandler<ActionEvent> {
            override fun handle(e: ActionEvent) {
                primaryStage.close()
            }
        });

        mainMenu.getItems().addAll(newRepository, signOut, exitCmd)
        menuBar.menus.add(mainMenu)
        root.setTop(menuBar)
    }
}