package com.example.maria_oliveira_dr3_tp1.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.maria_oliveira_dr3_tp1.Activities.EditarPacientes
import com.example.maria_oliveira_dr3_tp1.Model.Paciente
import com.example.maria_oliveira_dr3_tp1.Model.PacienteEConsulta
import com.example.maria_oliveira_dr3_tp1.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*



class PacientesAdapter (val pacientesInfoLista : List<PacienteEConsulta>, val context: Context, val startActivity : (Intent) -> Unit) : RecyclerView.Adapter<PacientesAdapter.PacienteViewHolder>() {

    class PacienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textoInformativo  = view.pacienteInfoTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recyclerview_item
                ,parent,false)

        val paciente = PacienteViewHolder(card)

        paciente.itemView.setOnClickListener{
            val pacienteclick = pacientesInfoLista [paciente.adapterPosition]
            val intent = Intent (context, EditarPacientes::class.java)
            intent.putExtra("pacienteeconsulta", pacienteclick)
            startActivity(intent)
        }
        return paciente
    }

    override fun getItemCount(): Int = pacientesInfoLista.size

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val info = pacientesInfoLista[position]
        holder.textoInformativo.text = info.toString()
    }
}
