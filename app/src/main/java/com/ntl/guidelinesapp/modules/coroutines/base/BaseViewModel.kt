package com.ntl.guidelinesapp.modules.coroutines.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ntl.guidelinesapp.modules.coroutines.common.Logger
import com.ntl.guidelinesapp.modules.coroutines.repositories.FakeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {

    protected var repository: FakeRepository = FakeRepository(Dispatchers.IO)

    var error = MutableLiveData<String>()
        protected set

    protected val parentExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Logger.log("Parent exception handler: ${throwable.message}")
            error.postValue(throwable.message)
        }

    open fun fetchData() {


    }

}