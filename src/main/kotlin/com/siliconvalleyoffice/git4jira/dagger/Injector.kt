package com.siliconvalleyoffice.git4jira.dagger

enum class Injector {
    Instance;

    lateinit var appComponent: AppComponent

    fun initialize() {
        appComponent = DaggerAppComponent.builder().build()
    }
}