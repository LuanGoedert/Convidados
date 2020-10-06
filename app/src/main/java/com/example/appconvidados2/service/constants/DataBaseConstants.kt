package com.example.appconvidados2.service.constants

class DataBaseConstants private constructor(){

    /**
    * tabelas disponiveis no banco de dados com suas colunas
    * */

    object GUEST {
        const val TABLE_NAME = "guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
            const val MARRIED = "married"
        }
    }
}