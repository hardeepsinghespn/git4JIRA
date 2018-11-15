package view

//import javafx.scene.layout.BorderPane
//import tornadofx.Stylesheet.Companion.menu
import com.siliconvalleyoffice.git4jira.app.APP_NAME
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.BorderPane
import com.siliconvalleyoffice.git4jira.controller.LoginController
import com.siliconvalleyoffice.git4jira.view.LoginView
import com.siliconvalleyoffice.git4jira.view.ClonedOriginView
import javafx.geometry.Orientation
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()
    private val userView: ClonedOriginView by inject()

    init {
        title = APP_NAME
        createMenuBar()
        root.center = splitpane {
            orientation = Orientation.HORIZONTAL
            this += userView
        }
    }

    fun createMenuBar() {

        val menuBar = MenuBar()
        val mainMenu = Menu("Menu")

        val newRepository = MenuItem("New Repository (Ctl-n)")

        val signOut = MenuItem("Sign Out (Ctl-d)")
        signOut.accelerator = KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN)
        signOut.setOnAction(object : EventHandler<ActionEvent> {
            override fun handle(e: ActionEvent) {
                LoginController.instance.logout()
                find(MainView::class).replaceWith(LoginView::class)
            }
        });

        val exitCmd = MenuItem("Quit (Ctl-q)")
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