package com.example.maria_oliveira_dr3_tp1.DAO

import androidx.room.*
import com.example.maria_oliveira_dr3_tp1.Model.Consulta
import com.example.maria_oliveira_dr3_tp1.Model.Paciente

@Dao
interface ConsultaDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun armazenar (consulta: Consulta)
    @Update
    fun atualizar (consulta: Consulta)
    @Delete
    fun apagar (consulta: Consulta)
}