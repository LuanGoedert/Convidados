package com.example.appconvidados2.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appconvidados2.service.constants.DataBaseConstants

// essa classe é criada pra fazer uma conexão entre o banco de dados e o repositorio
class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // se eu não tenho iniciado um banco de dados, essa função inicia um pra mim
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    //essa função é usada pra dar upgrade nas versões do app, sem perder os dados do usuario
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ConvidadoS.db"


        //O  "create table" cria a tabela e o que vai entre parenteses são as colunas da tabela
        private const val CREATE_TABLE_GUEST = ("create table" +
                DataBaseConstants.GUEST.TABLE_NAME + " ("
                + DataBaseConstants.GUEST.COLUMNS.ID + "integer primary key autoincrement, " // o ID vai se autoincrementaodo, nunca ficando com valores repetidos, sem usuarios repetidos, no caso
                + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer"
                + DataBaseConstants.GUEST.COLUMNS.MARRIED + " integer);")

    }
}