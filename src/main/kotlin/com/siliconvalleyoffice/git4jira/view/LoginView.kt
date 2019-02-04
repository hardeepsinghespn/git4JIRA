package com.siliconvalleyoffice.git4jira.view

import com.siliconvalleyoffice.git4jira.contracts.Login
import com.siliconvalleyoffice.git4jira.dagger.Injector
import com.siliconvalleyoffice.git4jira.dagger.LoginModule
import com.sun.prism.paint.Color
import com.sun.prism.paint.Paint
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.StackPane
import javafx.scene.text.Text
import tornadofx.*
import javax.inject.Inject

class LoginView : View(), Login.View {

    @Inject
    lateinit var loginController: Login.Controller

    override val root = StackPane()

    init {
        Injector.Instance.appComponent.plus(LoginModule(this)).inject(this)

        with(root) {
            setPrefSize(700.0, 400.0)
            style {
                backgroundColor += c("#000000", 0.8)
            }
            hbox(30) {
                paddingAll = 40.0
                vbox {
                    prefWidth = 300.0
                    paddingAll = 40.0
                    style {
                        backgroundColor += c("#FFFFFF", 1.0)
                    }
                    text {
                        headerTextStyle(25.px)
                        text = "GithHub"
                        marginBottom(40.0)
                    }
                    textfield {
                        textFieldStyle(15.px)
                        promptText = "Username"
                        marginBottom(20.0)
                    }
                    textfield {
                        textFieldStyle(15.px)
                        promptText = "Password"
                        marginBottom(30.0)
                    }
                    add(createLogInButton())
                }

                //Right
                vbox {
                    prefWidth = 300.0
                    paddingAll = 40.0
                    style {
                        backgroundColor += c("#FFFFFF", 1.0)
                    }
                    text {
                        headerTextStyle(25.px)
                        text = "JIRA"
                        marginBottom(40.0)
                    }
                    textfield {
                        border
                        textFieldStyle(15.px)
                        promptText = "Username"
                        marginBottom(20.0)
                    }
                    textfield {
                        textFieldStyle(15.px)
                        promptText = "Password"
                        marginBottom(30.0)
                    }
                    add(createLogInButton())
                }
            }
        }
    }

    //Create Login Button
    private fun createLogInButton() =
        hbox {
            alignment = Pos.BASELINE_RIGHT
            button("Log In") {
                style {
                    fontSize = 17.px
                    fontFamily = "Times New Roman"
                    backgroundColor += c("#000000", 0.8)
                    textFill = c("#FFFFFF", 1.0)
                }
            }
        }

    private fun Text.marginBottom(value: Double) {
        vboxConstraints {
            marginBottom = value
        }
    }

    private fun TextField.marginBottom(value: Double) {
        vboxConstraints {
            marginBottom = value
        }
    }

    private fun Text.headerTextStyle(fontDimension: Dimension<Dimension.LinearUnits>) {
        style {
            fontSize = fontDimension
            fontFamily = "Times New Roman"
        }
    }

    private fun TextField.textFieldStyle(fontDimension: Dimension<Dimension.LinearUnits>) {
        style {
            textBoxBorder = c("#00000000", 0.0)
            borderWidth += CssBox(1.px, 0.px, 0.px, 0.px)
            focusColor = c("#000000", 0.5)
            fontSize = fontDimension
            fontFamily = "Times New Roman"
        }
    }

    override fun updateStatus(status: String) {
        //Todo
    }
}