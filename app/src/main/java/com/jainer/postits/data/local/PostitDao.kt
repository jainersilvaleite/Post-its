package com.jainer.postits.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/*
* Define a interface PostitDao como sendo o DAO (Data Access Object) da PostitDatabase,
* de modo que todos os métodos relacionados ao tratamento dos dados armazenados no banco
* de dados em questão (similar a um SGBD) sejam declarados aqui para uso posterior
* */
@Dao
interface PostitDao {
    // retorna todos os post-its armazenados até o momento
    @Query("SELECT * FROM Postit ORDER BY createdAt DESC")
    fun getAllPostit(): LiveData<List<Postit>>

    // armazena um novo post-it no banco de dados
    @Insert fun addPostit(postit: Postit)

    // deleta o referido post-it do banco de dados
    @Delete fun deletePostit(postit: Postit)
}