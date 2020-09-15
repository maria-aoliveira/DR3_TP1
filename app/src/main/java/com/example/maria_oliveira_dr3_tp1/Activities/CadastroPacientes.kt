package com.example.maria_oliveira_dr3_tp1.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.maria_oliveira_dr3_tp1.Database.AppDatabase
import com.example.maria_oliveira_dr3_tp1.Database.AppDatabaseService
import com.example.maria_oliveira_dr3_tp1.Model.Consulta
import com.example.maria_oliveira_dr3_tp1.Model.Paciente
//import com.example.maria_oliveira_dr3_tp1.Database.AppDatabaseService
import com.example.maria_oliveira_dr3_tp1.R
import kotlinx.android.synthetic.main.activity_cadastro_pacientes.*
import java.lang.Exception

class CadastroPacientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pacientes)

        btnCadastrar.setOnClickListener {
            ListaPacientesTask().execute()
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class ListaPacientesTask: AsyncTask <Unit, Unit, Unit>(){
        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: Unit?) {
            try {
                val nome = txtNome.text.toString()
                val sobrenome = txtSobrenome.text.toString()
                val cpf = txtCpf.text.toString()
                val tipoConsulta = txtTipoConsulta.text.toString()
                val resultadoConsulta = txtResultadoConsulta.text.toString()

                val paciente = Paciente (nome, sobrenome, cpf.toInt())

                val appDatabase = AppDatabaseService.getInstance(applicationContext)

                appDatabase.pacienteDao().armazenar(paciente)
                val todosPacientes = appDatabase.pacienteDao().all()
                val pacienteId = todosPacientes[todosPacientes.lastIndex].id
                val consulta = Consulta (tipoConsulta, resultadoConsulta, pacienteId)
                appDatabase.consultaDAO().armazenar(consulta)

                finish()

            }catch (e:Exception){
                Log.e("Erro na Database", e.message)
            }
        }
    }
}


