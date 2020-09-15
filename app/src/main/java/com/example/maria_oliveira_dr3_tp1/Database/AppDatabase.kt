package com.example.maria_oliveira_dr3_tp1.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maria_oliveira_dr3_tp1.DAO.ConsultaDAO
import com.example.maria_oliveira_dr3_tp1.DAO.PacienteDAO
import com.example.maria_oliveira_dr3_tp1.Model.Consulta
import com.example.maria_oliveira_dr3_tp1.Model.Paciente

@Database
    (
    entities = arrayOf(
        Paciente::class,
        Consulta::class),
    version = 1
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun pacienteDao(): PacienteDAO
    abstract fun consultaDAO(): ConsultaDAO
}