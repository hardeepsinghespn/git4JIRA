package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_HEIGHT
import com.siliconvalleyoffice.git4jira.app.LOGIN_VIEW_WIDTH
import com.siliconvalleyoffice.git4jira.app.TEXTAREA_FONT_SIZE
import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.LoginModule
import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.geometry.Orientation
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.BorderPane
import tornadofx.*
import javax.inject.Inject

class LoginView : View(), Login.View {

    @Inject
    lateinit var loginController: Login.Controller

    override val root = BorderPane()
    var form: Form? = null
    var userNameTextfield: TextField? = null
    var passwordTextField: PasswordField? = null
    var loginButton: Button? = null

    init {
        Injector.Instance.appComponent.plus(LoginModule(this)).inject(this)
        title = "Github Login"

        initializeAccelerators()
        createLoginForm()
        with(root) { center = form }
        setPrimaryStageDimensions()
        root.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", TEXTAREA_FONT_SIZE));
    }


    private fun initializeAccelerators() {
        accelerators[KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)] = { primaryStage.close() }
        root.addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.ENTER) loginButton?.login()
        }
    }

    private fun createLoginForm() {
        form = form {
            fieldset(labelPosition = Orientation.HORIZONTAL) {
                field("Username") { userNameTextfield = textfield {} }
                field("Password") { passwordTextField = passwordfield {} }
                loginButton = button("Log in") {
                    isDefaultButton = true
                    useMaxWidth = true
                    spacing = 20.0
                    login()
                }
            }
        }
    }

    private fun Button.login() {
        action {
            runAsyncWithProgress {
                loginController.login(userNameTextfield?.text ?: "", passwordTextField?.text ?: "")
            }
        }
    }

    override fun updateStatus(status: String) {
        Platform.runLater {
            val alert = Alert(Alert.AlertType.ERROR, status, ButtonType.OK)
            alert.showAndWait()
        }
    }

    private fun setPrimaryStageDimensions() {
        primaryStage.minWidth = LOGIN_VIEW_WIDTH
        primaryStage.minHeight = LOGIN_VIEW_HEIGHT
    }
}