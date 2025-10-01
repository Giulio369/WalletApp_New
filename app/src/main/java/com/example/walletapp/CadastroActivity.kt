package com.example.walletapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val rbDebito = findViewById<RadioButton>(R.id.rbDebito)
        val rbCredito = findViewById<RadioButton>(R.id.rbCredito)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val etValor = findViewById<EditText>(R.id.etValor)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val tipo = if (rbDebito.isChecked) "debito" else "credito"
            val descricao = etDescricao.text.toString()
            val valor = etValor.text.toString().toDoubleOrNull()

            if (descricao.isNotEmpty() && valor != null) {
                val transacao = Transacao(tipo = tipo, descricao = descricao, valor = valor)
                val db = AppDatabase.getDatabase(this)

                lifecycleScope.launch {
                    db.transacaoDao().inserir(transacao)
                    runOnUiThread {
                        Toast.makeText(this@CadastroActivity, "Transação salva!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
