package com.example.walletapp

import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class ExtratoActivity : AppCompatActivity() {

    private lateinit var adapter: TransacaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        val rbTodos = findViewById<RadioButton>(R.id.rbTodos)
        val rbDebitos = findViewById<RadioButton>(R.id.rbDebitos)
        val rbCreditos = findViewById<RadioButton>(R.id.rbCreditos)
        val rvTransacoes = findViewById<RecyclerView>(R.id.rvTransacoes)
        val tvSaldo = findViewById<TextView>(R.id.tvSaldo)

        val db = AppDatabase.getDatabase(this)

        adapter = TransacaoAdapter(listOf())
        rvTransacoes.layoutManager = LinearLayoutManager(this)
        rvTransacoes.adapter = adapter

        fun atualizarLista(tipoFiltro: String?) {
            lifecycleScope.launch {
                val lista = when (tipoFiltro) {
                    "debito" -> db.transacaoDao().listarPorTipo("debito")
                    "credito" -> db.transacaoDao().listarPorTipo("credito")
                    else -> db.transacaoDao().listarTodas()
                }

                val todas = db.transacaoDao().listarTodas()
                var saldo = 0.0
                for (t in todas) {
                    saldo += if (t.tipo == "credito") t.valor else -t.valor
                }

                // Formatar saldo em reais
                val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                val saldoFormatado = formato.format(saldo)

                runOnUiThread {
                    adapter.atualizarLista(lista)
                    tvSaldo.text = "Saldo: $saldoFormatado"
                }
            }
        }


        rbTodos.setOnClickListener { atualizarLista(null) }
        rbDebitos.setOnClickListener { atualizarLista("debito") }
        rbCreditos.setOnClickListener { atualizarLista("credito") }

        atualizarLista(null) // carregar todas ao abrir

    fun atualizarFiltros() {
        rbTodos.setTextColor(if (rbTodos.isChecked) 0xFF6200EE.toInt() else 0xFF000000.toInt())
        rbDebitos.setTextColor(if (rbDebitos.isChecked) 0xFFF44336.toInt() else 0xFF000000.toInt())
        rbCreditos.setTextColor(if (rbCreditos.isChecked) 0xFF4CAF50.toInt() else 0xFF000000.toInt())
    }
    rbTodos.setOnClickListener {
        atualizarLista(null)
        atualizarFiltros()
    }
    rbDebitos.setOnClickListener {
        atualizarLista("debito")
        atualizarFiltros()
    }
    rbCreditos.setOnClickListener {
        atualizarLista("credito")
        atualizarFiltros()
    }
    }
}
