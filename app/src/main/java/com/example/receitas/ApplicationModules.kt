package com.example.receitas

import com.example.receitas.data.di.dataModule
import com.example.receitas.domain.di.domainModule
import com.example.receitas.presenter.di.presenterModule

object ApplicationModules {

    val applicationModules = arrayListOf(
        dataModule,
        domainModule,
        presenterModule
    )
}