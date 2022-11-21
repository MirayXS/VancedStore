package com.vanced.store.di

import com.vanced.store.ui.viewmodel.ThemesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val themesModule = module {
    viewModelOf(::ThemesViewModel)
}