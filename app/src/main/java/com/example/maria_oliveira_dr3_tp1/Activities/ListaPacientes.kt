package com.example.maria_oliveira_dr3_tp1.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.maria_oliveira_dr3_tp1.Adapter.PacientesAdapter
import com.example.maria_oliveira_dr3_tp1.Database.AppDatabase
import com.example.maria_oliveira_dr3_tp1.Database.AppDatabaseService
import com.example.maria_oliveira_dr3_tp1.Model.Consulta
import com.example.maria_oliveira_dr3_tp1.Model.Paciente
import com.example.maria_oliveira_dr3_tp1.Model.PacienteEConsulta
import com.example.maria_oliveira_dr3_tp1.R
import kotlinx.android.synthetic.main.activity_lista_pacientes.*


class ListaPacientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_pacientes)

        btnAddPacientes.setOnClickListener{
            startActivity(Intent(this, CadastroPacientes::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        ListaPacientesTask().execute()

    }

    @SuppressLint("StaticFieldLeak")
    inner class ListaPacientesTask : AsyncTask <
            Unit, Unit, List<PacienteEConsulta>
            >() {

        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(
                applicationContext,
                "A lista de pacientes está sendo carregada",
                Toast.LENGTH_SHORT)
                .show()
        }

        override fun doInBackground(vararg params: Unit?): List<PacienteEConsulta>{
            var appDatabase = AppDatabaseService.getInstance(applicationContext)
            var pacientes = appDatabase.pacienteDao().getPacienteEConsulta()
//            val retornar = mutableListOf<String>()
//            pacientes.forEach {
//                retornar.add(it.toString())
//            }
            return pacientes.toList()
        }

        override fun onPostExecute(result: List<PacienteEConsulta>?) {
            Toast.makeText(
                applicationContext,
                "Lista carregada",
                Toast.LENGTH_SHORT)
                .show()
            recyclerVwPacientes.adapter = PacientesAdapter(result!!, applicationContext, this@ListaPacientes::startActivity)
            recyclerVwPacientes.layoutManager = LinearLayoutManager(applicationContext)
         //   (recyclerVwPacientes.adapter as PacientesAdapter).notifyItemChanged(result!!.lastIndex)

        }
    }
}
