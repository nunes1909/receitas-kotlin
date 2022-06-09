package com.example.receitas.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.domain.model.Receita


@Database(
    entities = [Receita::class],
    version = 1,
    exportSchema = true
)
abstract class ReceitaDatabase : RoomDatabase() {

    abstract fun receitaDao(): ReceitaDao

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