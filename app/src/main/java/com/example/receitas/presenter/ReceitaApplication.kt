package com.example.receitas.presenter

import android.app.Application
import com.example.receitas.ApplicationModules.applicationModules
import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ReceitaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ReceitaApplication)
            module { applicationModules }
        }

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            salvaTipo()
            salvaNivel()
        }
    }

    private suspend fun salvaNivel() {
        try {
            val nivelReceitaDao = ReceitaDatabase
                .getInstance(this@ReceitaApplication).nivelReceitaDao()

            val nivel1 = NivelReceita(1, "Fácil")
            val nivel2 = NivelReceita(1, "Médio")
            val nivel3 = NivelReceita(1, "Difícil")
            nivelReceitaDao.salvaNivel(nivel1, nivel2, nivel3)
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun salvaTipo() {
        try {
            val tipoReceitaDao = ReceitaDatabase
                .getInstance(this@ReceitaApplication).tipoReceitaDao()

            val tipo1 = TipoReceita(id = 1, "Refeição")
            val tipo2 = TipoReceita(id = 2, "Refeição")
            val tipo3 = TipoReceita(id = 3, "Refeição")
            tipoReceitaDao.salvaTipo(tipo1, tipo2, tipo3)
        } catch (e: Exception) {
            throw e
        }
    }
}