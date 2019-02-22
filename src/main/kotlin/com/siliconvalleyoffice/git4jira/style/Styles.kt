package com.siliconvalleyoffice.git4jira.style

import com.siliconvalleyoffice.git4jira.constant.*
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
            fontSize = HEADING_FONT_SIZE.px
            fontWeight = FontWeight.BOLD
            textFill = Color.RED
        }
        tableColumn {
            fontSize = TABLE_COLUMN_FONT_SIZE.px
            fontWeight = FontWeight.MEDIUM
            textFill = Color.BLUE
        }
        title {
            fontSize = TITLE_FONT_SIZE.px
            fontWeight = FontWeight.BOLD
            textFill = Color.GREEN
        }
        menuBar {
            fontSize = MENUBAR_FONT_SIZE.px
            fontWeight = FontWeight.BOLD
        }
        textField {
            fontSize = TEXT_FIELD_FONT_SIZE.px
            fontWeight = FontWeight.MEDIUM
        }
        label {
            fontSize = LABEL_FONT_SIZE.px
            fontWeight = FontWeight.BOLD
        }
        button {
            fontSize = LABEL_FONT_SIZE.px
            fontWeight = FontWeight.BOLD
            textFill = Color.RED
            baseColor = Color.DARKGRAY
        }
        textArea {
            fontSize = TEXT_FIELD_FONT_SIZE.px
            fontWeight = FontWeight.MEDIUM
        }
        comboBox {
            fontSize = TEXT_FIELD_FONT_SIZE.px
            fontWeight = FontWeight.MEDIUM
        }
//        setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
//        root.center.setStyle("-fx-background-color: gray");
    }
}