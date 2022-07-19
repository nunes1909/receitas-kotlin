package com.example.receitas.data.mapper

import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.data.model.Receita
import com.example.receitas.domain.model.ReceitaDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataMapper(
    private val nivelReceitaDao: NivelReceitaDao,
    private val tipoReceitaDao: TipoReceitaDao
) {

    suspend fun paraFlowDomain(flowData: Flow<List<Receita>>) = flow<List<ReceitaDomain>> {
        flowData.collect { lista ->
            emit(
                lista.map { receita ->
                    paraDomain(receita)
                }
            )
        }
    }

    suspend fun paraDomain(receita: Receita): ReceitaDomain {
        val tipo = tipoReceitaDao.buscaDescricao(receita.tipoId)
        val nivel = nivelReceitaDao.buscaDescricao(receita.nivelId)

        return ReceitaDomain(
            id = receita.id,
            titulo = receita.titulo,
            tipo = tipo,
            nivel = nivel,
            ingredientes = receita.ingredientes,
            preparo = receita.preparo,
            imagem = receita.imagem,
            exibeImagem = receita.exibeImagem
        )
    }

    suspend fun paraData(receitaDomain: ReceitaDomain): Receita {
        val tipo = tipoReceitaDao.buscaId(receitaDomain.tipo)
        val nivel = nivelReceitaDao.buscaId(receitaDomain.nivel)

        return Receita(
            id = receitaDomain.id,
            titulo = receitaDomain.titulo,
            tipoId = tipo,
            nivelId = nivel,
            ingredientes = receitaDomain.ingredientes,
            preparo = receitaDomain.preparo,
            imagem = receitaDomain.imagem,
            exibeImagem = receitaDomain.exibeImagem
        )
    }

}