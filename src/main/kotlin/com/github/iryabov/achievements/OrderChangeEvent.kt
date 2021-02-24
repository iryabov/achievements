package com.github.iryabov.achievements

import net.n2oapp.criteria.api.CollectionPage
import net.n2oapp.criteria.dataset.DataSet
import net.n2oapp.framework.api.exception.N2oException
import net.n2oapp.framework.api.processing.DataProcessing
import net.n2oapp.framework.api.ui.ActionRequestInfo
import net.n2oapp.framework.api.ui.ActionResponseInfo
import net.n2oapp.framework.api.ui.QueryRequestInfo
import net.n2oapp.framework.api.ui.QueryResponseInfo
import org.springframework.stereotype.Component

@Component
class OrderChangeEvent(val userService: UserService): DataProcessing {
    override fun processAction(requestInfo: ActionRequestInfo<DataSet>?,
                               responseInfo: ActionResponseInfo?,
                               dataSet: DataSet?) {}

    override fun processActionError(requestInfo: ActionRequestInfo<DataSet>?,
                                    responseInfo: ActionResponseInfo?,
                                    dataSet: DataSet?) {}

    override fun processActionResult(requestInfo: ActionRequestInfo<DataSet>?,
                                     responseInfo: ActionResponseInfo?,
                                     dataSet: DataSet?) {
        if (requestInfo?.operation?.id == "order") {
            requestInfo.user.set(userService.loadUserData(dataSet!!["email"] as String))
        }
    }

    override fun processQuery(requestInfo: QueryRequestInfo?,
                              responseInfo: QueryResponseInfo?) {}

    override fun processQueryError(requestInfo: QueryRequestInfo?,
                                   responseInfo: QueryResponseInfo?,
                                   exception: N2oException?) {}

    override fun processQueryResult(requestInfo: QueryRequestInfo?,
                                    responseInfo: QueryResponseInfo?,
                                    page: CollectionPage<DataSet>?) {}

}