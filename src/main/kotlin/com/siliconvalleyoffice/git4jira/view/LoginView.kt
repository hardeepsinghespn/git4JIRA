package loginapp.views

import com.siliconvalleyoffice.git4jira.app.APP_NAME
import javafx.beans.property.SimpleStringProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import loginapp.controllers.LoginController
import tornadofx.*

class LoginView : View() {

    override val root = BorderPane()
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }
    val loginController = LoginController.instance

    val form = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Username") {
                textfield(username).required()
            }
            field("Password") {
                passwordfield(password).required()
            }
            button("Log in") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        loginController.login(username.value, password.value)
                    }
                }
            }
        }
        label(loginController.statusProperty) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }
    }

    init {
        title = "GitHub Login"
        root.center = form
        createMenuBar()
        setGlobalEventHandler(root)
    }

    override fun onDock() {
        username.value = ""
        password.value = ""
        model.clearDecorators()
    }

    fun createMenuBar() {

        val menuBar = MenuBar()
        val mainMenu = Menu("Menu")

        val exitCmd = MenuItem("Exit (Ctl-q)")
        exitCmd.accelerator = KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        exitCmd.setOnAction(object : EventHandler<ActionEvent> {
            override fun handle(e: ActionEvent) {
                primaryStage.close()
            }
        });

        mainMenu.getItems().addAll(exitCmd)
        menuBar.menus.add(mainMenu)
        root.setTop(menuBar)
    }

    private fun setGlobalEventHandler(root: Node) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, { ev ->
            if (ev.getCode() == KeyCode.ENTER) {
                loginController.login(username.value, password.value)
            } else if (ev.getCode() == KeyCode.Q) {
                primaryStage.close()
                }
            ev.consume()
        })
    }
}