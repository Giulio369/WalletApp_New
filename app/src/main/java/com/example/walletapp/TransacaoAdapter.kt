package com.example.walletapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class TransacaoAdapter(private var transacoes: List<Transacao>) :
    RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder>() {

    class TransacaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivTipo: ImageView = itemView.findViewById(R.id.ivTipo)
        val tvDescricao: TextView = itemView.findViewById(R.id.tvDescricao)
        val tvValor: TextView = itemView.findViewById(R.id.tvValor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transacao, parent, false)
        return TransacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        val transacao = transacoes[position]

        // Formatar valor em reais
        val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        val valorFormatado = formato.format(transacao.valor)

        holder.tvDescricao.text = transacao.descricao
        holder.tvValor.text = valorFormatado

        if (transacao.tipo == "credito") {
            holder.ivTipo.setImageResource(R.drawable.ic_credito)
            holder.tvValor.setTextColor(0xFF4CAF50.toInt())
            holder.tvDescricao.setTextColor(0xFF4CAF50.toInt())
        } else {
            holder.ivTipo.setImageResource(R.drawable.ic_debito)
            holder.tvValor.setTextColor(0xFFF44336.toInt())
            holder.tvDescricao.setTextColor(0xFFF44336.toInt())
        }
    }


    override fun getItemCount(): Int = transacoes.size

    fun atualizarLista(novasTransacoes: List<Transacao>) {
        transacoes = novasTransacoes
        notifyDataSetChanged()
    }
}
