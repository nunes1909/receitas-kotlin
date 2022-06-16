package com.example.receitas.presenter

import android.app.Application
import com.example.receitas.AppModules
import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ReceitaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ReceitaApplication)
            modules(AppModules.modules)
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

            val nivel1 = NivelReceita(0, "Fácil")
            val nivel2 = NivelReceita(1, "Médio")
            val nivel3 = NivelReceita(2, "Difícil")
            nivelReceitaDao.salvaNivel(nivel1, nivel2, nivel3)
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun salvaTipo() {
        try {
            val tipoReceitaDao = ReceitaDatabase
                .getInstance(this@ReceitaApplication).tipoReceitaDao()
            val tipo0 = TipoReceita(id = 0, "Refeição")
            val tipo1 = TipoReceita(id = 1, "Saudável")
            val tipo2 = TipoReceita(id = 2, "Lanche")
            val tipo3 = TipoReceita(id = 3, "Sobremesa")
            val tipo4 = TipoReceita(id = 4, "Rápido")
            val tipo5 = TipoReceita(id = 5, "Drink")
            tipoReceitaDao.salvaTipo(tipo0, tipo1, tipo2, tipo3, tipo4, tipo5)
        } catch (e: Exception) {
            throw e
        }
    }
}