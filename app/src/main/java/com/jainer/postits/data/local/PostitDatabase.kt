package com.jainer.postits.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/*
* Define o banco de dados que conterá as entidades de dados utilizadas (apenas Postit, nesse caso)
* e será responsável por retornar seus respectivos DAOs. A RoomDB oferece uma camada de abstração
* ao SQLite, portanto a classe e funções neste caso devem ser abstratas, já que não seremos nós
* a implementá-los. De forma similar, boa parte do código relacionado ao uso da RoomDB segue de
* forma a não implementar recursos diretamente, trabalhando mais em cima de sua abstração.
* */
@Database(entities = [Postit::class], version = 1)
@TypeConverters(PostitConverters::class) // referência os conversores aplicados ao banco de dados
abstract class PostitDatabase: RoomDatabase() {
    abstract fun getPostitDao(): PostitDao
}