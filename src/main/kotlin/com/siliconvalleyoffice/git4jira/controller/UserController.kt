package com.siliconvalleyoffice.git4jira.controller

import loginapp.controllers.LoginController
import tornadofx.*
import javafx.scene.control.ComboBox
import javafx.collections.FXCollections
import javafx.collections.ObservableList



class UserController private constructor() : Controller() {
    private object Holder { val INSTANCE = UserController() }
    companion object {
        val instance: UserController by lazy { Holder.INSTANCE }
    }

    var options = FXCollections.observableArrayList(
            "Option 1",
            "Option 2",
            "Option 3"
    )
    val comboBox = ComboBox(options)
}