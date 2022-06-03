package com.ntl.guidelinesapp.modules.coroutines.singlerequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ntl.guidelinesapp.modules.coroutines.base.BaseViewModel
import com.ntl.guidelinesapp.modules.coroutines.common.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SingleViewModel : BaseViewModel() {
    var data = MutableLiveData<Int>()
        private set

    private lateinit var parentJob: Job

    override fun fetchData() {
        parentJob = viewModelScope.launch(parentExceptionHandler) {
            val deferred = async { repository.requestWithIndex(4, true) }
            val result = deferred.await()
            data.postValue(result)
        }

        parentJob.invokeOnCompletion {
            // TODO: update ui
            Logger.log("parent job finish")
        }
    }

    fun cancel() {
        parentJob.cancel()
    }
}