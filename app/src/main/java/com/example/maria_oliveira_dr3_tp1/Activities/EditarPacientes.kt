package com.example.maria_oliveira_dr3_tp1.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.maria_oliveira_dr3_tp1.Adapter.PacientesAdapter
import com.example.maria_oliveira_dr3_tp1.Database.AppDatabaseService
import com.example.maria_oliveira_dr3_tp1.Model.Consulta
import com.example.maria_oliveira_dr3_tp1.Model.Paciente
import com.example.maria_oliveira_dr3_tp1.Model.PacienteEConsulta
import com.example.maria_oliveira_dr3_tp1.R
import kotlinx.android.synthetic.main.activity_editar_pacientes.*
import java.lang.Exception

class EditarPacientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pacientes)

        val pacienteClick = intent.getSerializableExtra("pacienteeconsulta") as PacienteEConsulta

        btnEdit.setOnClickListener {
            editarPaciente().execute(pacienteClick)
        }

        btnDelete.setOnClickListener {
            deletarPaciente().execute(pacienteClick)
        }
    }

    inner class editarPaciente : AsyncTask<PacienteEConsulta, Unit, Unit>() {

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: PacienteEConsulta?) {
            val appDatabase = AppDatabaseService.getInstance(applicationContext)
            val nomeEdt = txtNomeEdt.text.toString()
            val sobrenomeEdt = txtSobrenomeEdt.text.toString()
            val cpfEdt = txtCpfEdt.text.toString().toInt()
            val tipoConsultaEdt = txtTipoConsultaEdt.text.toString()
            val resultadoConsultaEdt =  txtResultadoConsultaEdt.text.toString()

            appDatabase.pacienteDao().atualizar(
                Paciente(nomeEdt,sobrenomeEdt,cpfEdt,params[0]!!.
                paciente.id))

            appDatabase.consultaDAO().atualizar(
                Consulta(tipoConsultaEdt,resultadoConsultaEdt,
                params[0]!!.consulta!!.paciente_id,
                params[0]!!.consulta!!.id))
        }

        override fun onPostExecute(result: Unit?) {
            Toast.makeText(
                applicationContext,
                "Informações do paciente foram editadas com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    inner class deletarPaciente : AsyncTask<PacienteEConsulta, Unit, Unit>() {

        override fun doInBackground(vararg params: PacienteEConsulta?) {

            val appDatabase = AppDatabaseService.getInstance(applicationContext)
            appDatabase.pacienteDao().apagar(params[0]!!.paciente)
            appDatabase.consultaDAO().apagar(params[0]!!.consulta!!)
        }

        override fun onPostExecute(result: Unit?) {
            Toast.makeText(
                applicationContext,
                "Informações do paciente foram deletadas com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }
}
