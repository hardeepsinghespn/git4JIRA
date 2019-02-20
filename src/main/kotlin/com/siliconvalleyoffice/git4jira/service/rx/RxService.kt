package com.siliconvalleyoffice.git4jira.service.rx

import io.reactivex.subjects.PublishSubject

class RxService {

    enum class ProjectProfileAction {
        UPDATE_LIST
    }

    val projectProfileSubject = PublishSubject.create<ProjectProfileAction>()
}