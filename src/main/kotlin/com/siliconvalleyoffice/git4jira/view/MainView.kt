package view

import com.github.thomasnield.rxkotlinfx.actionEvents
import javafx.geometry.Orientation
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()

    init {
        title = "git-4-JIRA"
    }
}