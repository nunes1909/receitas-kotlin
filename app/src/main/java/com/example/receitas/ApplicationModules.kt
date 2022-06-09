package com.example.receitas

import com.example.receitas.domain.di.domainModule
import com.example.receitas.presenter.di.presenterModule

object ApplicationModules {

    val applicationModules = arrayListOf(
        domainModule,
        presenterModule
    )
}