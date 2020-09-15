package com.example.maria_oliveira_dr3_tp1.DAO

import androidx.room.*
import com.example.maria_oliveira_dr3_tp1.Model.Paciente
import com.example.maria_oliveira_dr3_tp1.Model.PacienteEConsulta

@Dao
interface PacienteDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun armazenar (paciente: Paciente)

    @Update
    fun atualizar (paciente: Paciente)

    @Delete
    fun apagar (paciente: Paciente)

    @Query ("SELECT * FROM Paciente")
    fun all (): Array<Paciente>

    @Transaction
    @Query("SELECT * FROM Paciente")
    fun getPacienteEConsulta(): List<PacienteEConsulta>

//    @Transaction
//    @Query("SELECT * FROM Paciente WHERE id = :indicador")
//    fun show (indicador: Int): List<Paciente>
}