package loginapp.views

import com.siliconvalleyoffice.git4jira.app.APP_NAME
import javafx.beans.property.SimpleStringProperty
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
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import loginapp.controllers.LoginController
import tornadofx.*

class LoginView : View() {

    override val root = BorderPane()
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }
    val loginController: LoginController by inject()

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
//                setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
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
    }

    override fun onDock() {
        username.value = ""
        password.value = ""
        model.clearDecorators()
    }

    fun createMenuBar() {

        val menuBar = MenuBar()
        val mainMenu = Menu("Menu")

        val newRepository = MenuItem("New Repository (Ctl-n)")

        val exitCmd = MenuItem("Exit (Ctl-q)")
        exitCmd.accelerator = KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        exitCmd.setOnAction(object : EventHandler<ActionEvent> {
            override fun handle(e: ActionEvent) {
                primaryStage.close()
            }
        });

        mainMenu.getItems().addAll(newRepository, exitCmd)
        menuBar.menus.add(mainMenu)
        root.setTop(menuBar)
    }
}