package com.github.iryabov.achievements

import net.n2oapp.criteria.dataset.DataSet
import net.n2oapp.framework.api.processing.N2oModule
import net.n2oapp.framework.api.ui.ActionRequestInfo
import net.n2oapp.framework.api.ui.ActionResponseInfo
import org.springframework.stereotype.Component

@Component
class OrderChangeEvent(val userService: UserService,
                       val notificationService: NotificationService) : N2oModule() {

    override fun processActionResult(requestInfo: ActionRequestInfo<DataSet>?,
                                     responseInfo: ActionResponseInfo?,
                                     dataSet: DataSet?) {
        if (requestInfo?.operation?.id == "order") {
            val email = dataSet!!["email"] as String
            requestInfo.user.set(userService.loadUserData(email))
            val order = dataSet["award.name"] as String
            notificationService.notifyAboutOrder(email, order)
        }
    }
}