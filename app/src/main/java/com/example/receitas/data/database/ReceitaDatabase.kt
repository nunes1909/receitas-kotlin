package com.example.receitas.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.receitas.data.database.converters.Converter
import com.example.receitas.data.formulario.dao.NivelReceitaDao
import com.example.receitas.data.receita.dao.ReceitaDao
import com.example.receitas.data.formulario.dao.TipoReceitaDao
import com.example.receitas.data.formulario.model.Nivel
import com.example.receitas.data.receita.model.Receita
import com.example.receitas.data.formulario.model.TipoReceita

@Database(
    entities = [
        Receita::class,
        Nivel::class,
        TipoReceita::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class ReceitaDatabase : RoomDatabase() {

    abstract fun receitaDao(): ReceitaDao
    abstract fun nivelReceitaDao(): NivelReceitaDao
    abstract fun tipoReceitaDao(): TipoReceitaDao

    companion object {
        private const val DB_NAME = "receitas.db"

        @Volatile
        private var db: ReceitaDatabase? = null

        fun getInstance(context: Context): ReceitaDatabase {
            return db ?: Room.databaseBuilder(
                context,
                ReceitaDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build().also {
                    db = it
                }
        }
    }
}