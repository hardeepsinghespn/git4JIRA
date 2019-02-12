package com.siliconvalleyoffice.git4jira.service

import com.siliconvalleyoffice.git4jira.contracts.Service
import javax.json.JsonObject


class LoginService: Service.Login {

    override fun login(username: String, password: String): JsonObject? {
        //TODO: Under Construction
        return null
    }

    override fun logout() {
        //TODO: Under Construction
    }
}