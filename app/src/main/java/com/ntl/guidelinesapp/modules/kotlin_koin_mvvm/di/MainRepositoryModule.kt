package com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.di

import com.ntl.guidelinesapp.modules.kotlin_koin_mvvm.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainRepositoryModule = module {
    single { MainRepository() }
}