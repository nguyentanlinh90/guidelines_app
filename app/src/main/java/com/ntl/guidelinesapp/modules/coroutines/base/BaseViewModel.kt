package com.ntl.guidelinesapp.modules.coroutines.base

import androidx.lifecycle.ViewModel
import com.ntl.guidelinesapp.modules.coroutines.repositories.FakeRepository
import kotlinx.coroutines.Dispatchers

open class BaseViewModel: ViewModel() {

    protected var repository: FakeRepository = FakeRepository(Dispatchers.IO)

    open fun fetchData() {


    }

}