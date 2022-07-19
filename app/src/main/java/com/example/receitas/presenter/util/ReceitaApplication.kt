package com.example.receitas.presenter.util

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.di.dataModules
import com.example.receitas.domain.di.domainModules
import com.example.receitas.data.formulario.model.Nivel
import com.example.receitas.data.formulario.model.TipoReceita
import com.example.receitas.presenter.di.presenterModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ReceitaApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ReceitaApplication)
            modules(presenterModules + domainModules + dataModules)
        }

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            salvaTipo()
            salvaNivel()
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }

    private suspend fun salvaNivel() {
        try {
            val nivelReceitaDao = ReceitaDatabase
                .getInstance(this@ReceitaApplication).nivelReceitaDao()

            val nivel1 = Nivel(0, "Fácil")
            val nivel2 = Nivel(1, "Médio")
            val nivel3 = Nivel(2, "Difícil")
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
            val tipo3 = TipoReceita(id = 3, "Doce")
            val tipo4 = TipoReceita(id = 4, "Drink")
            tipoReceitaDao.salvaTipo(tipo0, tipo1, tipo2, tipo3, tipo4)
        } catch (e: Exception) {
            throw e
        }
    }
}