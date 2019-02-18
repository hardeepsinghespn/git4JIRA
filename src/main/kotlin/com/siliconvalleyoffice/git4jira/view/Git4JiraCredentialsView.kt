package view

import com.siliconvalleyoffice.git4jira.app.CREDENTIALS_VIEW
import com.siliconvalleyoffice.git4jira.contracts.CreateProject
import com.siliconvalleyoffice.git4jira.contracts.Git4JiraCredentials
import com.siliconvalleyoffice.git4jira.dagger.CreateProjectModule
import com.siliconvalleyoffice.git4jira.dagger.Git4JiraCredentialsModule
import com.siliconvalleyoffice.git4jira.dagger.Injector
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*
import javax.inject.Inject

class Git4JiraCredentialsView : View(), Git4JiraCredentials.View {

    @Inject
    lateinit var git4JiraCredentialsController: Git4JiraCredentials.Controller

    override val root: BorderPane by fxml(CREDENTIALS_VIEW)

    val encryptionPhrase: TextField by fxid("encryptionPhrase")
    val encryptionKey: TextField by fxid("encryptionKey")
    val validateButton: Button by fxid("validateButton")
    val EMPTY = ""

    init {
        Injector.Instance.appComponent.plus(Git4JiraCredentialsModule(this)).inject(this)
        this.title = "git4JIRA Credentials"
        assignButtonListeners()
    }

    private fun assignButtonListeners() {
        validateButton.setOnMouseClicked { git4JiraCredentialsController.onValidateClick() }
    }

    override fun encryptionPhrase() = encryptionPhrase.text ?: EMPTY

    override fun encryptionKey() = encryptionKey.text ?: EMPTY

    override fun closeView() = close()
}
