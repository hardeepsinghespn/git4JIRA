package com.siliconvalleyoffice.git4jira.controller

import tornadofx.*

class ClonedOriginController private constructor() : Controller() {
    private object Holder { val INSTANCE = ClonedOriginController() }
    companion object {
        val instance: ClonedOriginController by lazy { Holder.INSTANCE }
    }
}