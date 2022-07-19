package com.example.receitas.data.formulario.repository

import com.example.receitas.data.formulario.dao.NivelReceitaDao
import com.example.receitas.data.formulario.dao.TipoReceitaDao
import com.example.receitas.data.formulario.mapper.NivelDataMapper
import com.example.receitas.domain.model.NivelDomain
import com.example.receitas.domain.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class FormularioRepositoryImpl(
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao,
    private val nivelDataMapper: NivelDataMapper
): FormularioRepository {
    // Busca os tipos de receita
    override fun buscaTipoValues(): Flow<List<String>> {
        return tipoDao.buscaTipos()
    }

    // Busca os niveis de receita
    override suspend fun buscaNivelValues(): Flow<List<NivelDomain>> {
        val flowNivel = nivelDao.buscaNiveis()
        return nivelDataMapper.paraFlowDomain(flowNivel)
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