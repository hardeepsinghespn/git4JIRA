package com.siliconvalleyoffice.git

import com.siliconvalleyoffice.git4jira.controller.LoginControllerOld
import javafx.util.StringConverter
import org.eclipse.egit.github.core.Repository


class RepositoryConverter : StringConverter<Repository>() {

    override fun toString(repository : Repository) : String {
        return repository?.name ?: ""
    }

    override fun fromString(name : String) : Repository? {
        var theRepository = Repository()
        if (!name .isEmpty()) {
            val repositoryList = LoginControllerOld.INSTANCE.gitHubController.getForkListProperty().get()
            val filterResult = repositoryList.filter { it.name.equals(name, true) }
            if (filterResult.size > 0) theRepository = filterResult.get(0)
        }
        return theRepository
    }
}
