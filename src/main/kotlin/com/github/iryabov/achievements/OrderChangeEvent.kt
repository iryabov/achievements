package com.github.iryabov.achievements

import net.n2oapp.criteria.dataset.DataSet
import net.n2oapp.framework.api.processing.N2oModule
import net.n2oapp.framework.api.ui.ActionRequestInfo
import net.n2oapp.framework.api.ui.ActionResponseInfo
import org.springframework.stereotype.Component

@Component
class OrderChangeEvent(val userService: UserService): N2oModule() {

    override fun processActionResult(requestInfo: ActionRequestInfo<DataSet>?,
                                     responseInfo: ActionResponseInfo?,
                                     dataSet: DataSet?) {
        if (requestInfo?.operation?.id == "order") {
            requestInfo.user.set(userService.loadUserData(dataSet!!["email"] as String))
        }
    }
}