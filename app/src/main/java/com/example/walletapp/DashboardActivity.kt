package com.example.walletapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnCadastro = findViewById<Button>(R.id.btnCadastro)
        val btnExtrato = findViewById<Button>(R.id.btnExtrato)
        val btnSair = findViewById<ImageButton>(R.id.btnSair)

        btnCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        btnExtrato.setOnClickListener {
            startActivity(Intent(this, ExtratoActivity::class.java))
        }

        btnSair.setOnClickListener {
            finishAffinity()
        }
    }
}
