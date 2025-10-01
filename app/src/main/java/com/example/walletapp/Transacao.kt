package com.example.walletapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transacoes")
data class Transacao(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String, // "debito" ou "credito"
    val descricao: String,
    val valor: Double
)
