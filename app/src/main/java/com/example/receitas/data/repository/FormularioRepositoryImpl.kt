package com.example.receitas.data.repository

import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.domain.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class FormularioRepositoryImpl(
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao
): FormularioRepository {
    // Busca os tipos de receita
    override fun buscaTipoValues(): Flow<List<String>> {
        return tipoDao.buscaTipos()
    }

    // Busca os niveis de receita
    override fun buscaNivelValues(): Flow<List<String>> {
        return nivelDao.buscaNiveis()
    }

    override suspend fun buscaTipoIdPelaDescricao(descricao: String): Int {
        return tipoDao.buscaId(descricao)
    }

    override suspend fun buscaTipoDescricaoPeloId(id: Int): String {
        return tipoDao.buscaDescricao(id)
    }

    override suspend fun buscaNivelIdPelaDescricao(descricao: String): Int {
        return nivelDao.buscaId(descricao)
    }

    override suspend fun buscaNivelDescricaoPeloId(id: Int): String {
        return nivelDao.buscaDescricao(id)
    }
}