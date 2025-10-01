package com.example.walletapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransacaoDao {

    @Insert
    suspend fun inserir(transacao: Transacao)

    @Query("SELECT * FROM transacoes")
    suspend fun listarTodas(): List<Transacao>

    @Query("SELECT * FROM transacoes WHERE tipo = :tipo")
    suspend fun listarPorTipo(tipo: String): List<Transacao>
}
