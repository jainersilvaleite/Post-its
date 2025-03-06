package com.jainer.postits.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/*
* Define a classe de dados Postit como sendo uma entidade de dados da PostitDatabase,
* que por sua vez é um banco de dados instanciado pela dependência RoomDB
* */
@Entity
data class Postit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String,
    val createdAt: Date
)