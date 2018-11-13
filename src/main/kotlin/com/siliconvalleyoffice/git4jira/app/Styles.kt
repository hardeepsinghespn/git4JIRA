package com.siliconvalleyoffice.git4jira.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.stage.Screen
import tornadofx.*


val pixelDensityFactor = ((Screen.getPrimary().getDpi() / 96) + 1).toInt()

class Styles : Stylesheet() { companion object {
    val heading by cssclass()
    val tableColumn by cssclass()
    val title by cssclass()
}

    init {
        label and heading {
            padding = box(0.px,0.px,0.px,5.px)
            fontSize = 14.px * pixelDensityFactor
            fontWeight = FontWeight.BOLD
            textFill = Color.RED
        }

        tableColumn {
            fontSize = 11.px * pixelDensityFactor
            fontWeight = FontWeight.BOLD
            textFill = Color.BLUE
        }

        title {
            fontSize = 12.px * pixelDensityFactor
            fontWeight = FontWeight.BOLD
            textFill = Color.GREEN
        }
    }
}