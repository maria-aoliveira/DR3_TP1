package com.example.maria_oliveira_dr3_tp1.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Paciente (
    var nome: String,
    var sobrenome: String,
    var cpf: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
) : Serializable