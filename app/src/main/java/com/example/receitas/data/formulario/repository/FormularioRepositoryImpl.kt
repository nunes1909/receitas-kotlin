package com.example.receitas.data.formulario.repository

import com.example.receitas.data.formulario.dao.NivelDao
import com.example.receitas.data.formulario.dao.TipoDao
import com.example.receitas.data.formulario.mapper.NivelDataMapper
import com.example.receitas.data.formulario.mapper.TipoDataMapper
import com.example.receitas.domain.formulario.model.NivelDomain
import com.example.receitas.domain.formulario.model.TipoDomain
import com.example.receitas.domain.formulario.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class FormularioRepositoryImpl(
    private val tipoDao: TipoDao,
    private val nivelDao: NivelDao,
    private val nivelDataMapper: NivelDataMapper,
    private val tipoDataMapper: TipoDataMapper
): FormularioRepository {
    // Busca os tipos de receita
    override suspend fun buscaTipoValues(): Flow<List<TipoDomain>> {
        val flowTipo = tipoDao.buscaTipos()
        return tipoDataMapper.paraFlowDomain(flowTipo)
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