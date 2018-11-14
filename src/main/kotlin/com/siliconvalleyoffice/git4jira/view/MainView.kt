package view

//import javafx.scene.layout.BorderPane
//import tornadofx.Stylesheet.Companion.menu
import com.siliconvalleyoffice.git4jira.app.APP_NAME
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import tornadofx.*
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import javafx.scene.layout.Pane
import javafx.stage.Stage
import loginapp.views.LoginView


//import tornadofx.View
//import tornadofx.Stylesheet.Companion.menuItem
//import tornadofx.Stylesheet.Companion.separator


class MainView : View() {
    override val root = BorderPane()

    init {
        title = APP_NAME
        createMenuBar()
    }

    fun createMenuBar() {

        val menuBar = MenuBar()
        val mainMenu = Menu("Menu")

        val newRepository = MenuItem("New Repository (Ctl-n)")

        val signOut = MenuItem("Sign Out (Ctl-d)")
        signOut.accelerator = KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN)
        signOut.setOnAction(object : EventHandler<ActionEvent> {
            override fun handle(e: ActionEvent) {
                find(MainView::class).replaceWith(LoginView::class)
            }
        });

        val exitCmd = MenuItem("Exit (Ctl-q)")
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