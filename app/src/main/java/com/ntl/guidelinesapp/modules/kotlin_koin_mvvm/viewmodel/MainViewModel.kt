package com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.repository.MainRepository

class MainViewModel(mainRepository: MainRepository) : ViewModel() {
    private var _count: MutableLiveData<Int> = MutableLiveData()
    val count = _count

    init {
        _count.value = mainRepository.getData()
    }

    fun increase() {
        _count.value = _count.value?.plus(1)
    }
}