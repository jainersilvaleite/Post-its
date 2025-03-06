package com.jainer.postits

import android.app.Application
import androidx.room.Room
import com.jainer.postits.data.local.PostitDatabase

// inicializa o banco de dados dentro do contexto do aplicativo conforme sua abstração
class MainApplication: Application() {
    // objeto interno definido com o propósito de utilizar o banco de dados na ViewModel
    companion object {
        lateinit var postitDatabase: PostitDatabase
    }

    override fun onCreate() {
        super.onCreate()
        postitDatabase = Room.databaseBuilder(
            applicationContext,
            PostitDatabase::class.java,
            "post-its-database"
        ).build()
    }
}