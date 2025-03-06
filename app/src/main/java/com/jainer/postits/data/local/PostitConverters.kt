package com.jainer.postits.data.local

import androidx.room.TypeConverter
import java.util.Date

// recurso bastante útil da RoomDB que nos permite definir conversões de valores automaticamente
class PostitConverters {
    /*
    * O banco de dados trabalha com datas expressas em Longs, logo é necessário aplicar um
    * conversor para este tipo, de modo que durante o processo de persistência no banco de dados
    * criado pelo RoomDB, será realizada a conversão de qualquer valor do tipo Date em Long
    * */
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    // o oposto também deve ocorrer quando estamos trazendo este dado do banco para o aplicativo
    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }
}