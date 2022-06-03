package com.ntl.guidelinesapp.modules.coroutines.multiplerequest.sequential

import androidx.lifecycle.viewModelScope
import com.ntl.guidelinesapp.modules.coroutines.base.BaseViewModel
import com.ntl.guidelinesapp.modules.coroutines.common.Logger
import kotlinx.coroutines.launch

class SequentialViewModel : BaseViewModel() {
    override fun fetchData() {

        //request tuan tu, xong cai 1 roi moi den cai 2
        val parentJob = viewModelScope.launch(parentExceptionHandler) {
            // TODO: show loading
            Logger.log("Show loading")
            repository.requestWithIndex(1, true) // cai 1
            repository.requestWithIndex(2) // cai 2

            //Logger.log("Hidden loading")
        }
        //du co exception hay ko thi van nhay vao day, de hide loading
        parentJob.invokeOnCompletion {
            Logger.log("Hidden loading")
        }
    }
}