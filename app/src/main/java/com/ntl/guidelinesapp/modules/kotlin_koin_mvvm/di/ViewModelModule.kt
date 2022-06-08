package com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.di

import com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel { MainViewModel(get()) }
}