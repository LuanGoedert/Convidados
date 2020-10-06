package com.example.appconvidados2.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.appconvidados2.service.constants.DataBaseConstants
import com.example.appconvidados2.service.model.GuestModel
import java.util.ArrayList

class GuestRepository private constructor(context: Context) {

    //!!IMPORTANTE!!
    //o cursor é o objeto que vai lendo os dados da coluna, um á um

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    //esse metodo estatico retorna uma instancia da classe, sendo esse o conceito do metodo Singleton, somente esse metodo te da a instancia da classe
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun get(id: Int): GuestModel? {

        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                DataBaseConstants.GUEST.COLUMNS.MARRIED
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                //pegando o valor do nome  e passando pro cursor
                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                val married =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.MARRIED)) == 1)

                guest = GuestModel(id, name, presence, married)
            }
            cursor?.close()

            guest
        } catch (e: Exception) {
            guest
        }
    }

    //Singleton -> somente umas instancia da classe, só tendo uma conexão com o banco de dados , evitando concorrencia entre classe com o banco de dados
    //primeiramente deixan a clase com contrutor privado

    // em vez de colocar os dados que precisa salvar, só instancia um guestModel que ja injeta a dependencia GuestModel
    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.MARRIED, guest.married)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }
    }


    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                DataBaseConstants.GUEST.COLUMNS.MARRIED
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //pegando o valor do nome  e passando pro cursor
                    val id =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                    val married =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.MARRIED)) == 1)

                    val guest = GuestModel(id, name, presence, married)
                    list.add(guest)
                }
            }
            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }
        list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery(
                "SELECT id, name, presence, married FROM Guest WHERE presence = 1",
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //pegando o valor do nome  e passando pro cursor
                    val id =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                    val married =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.MARRIED)) == 1)

                    val guest = GuestModel(id, name, presence, married)
                    list.add(guest)
                }
            }
            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }
        list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery(
                "SELECT id, name, presence, married FROM Guest WHERE presence = 0",
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //pegando o valor do nome  e passando pro cursor
                    val id =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                    val married =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.MARRIED)) == 1)

                    val guest = GuestModel(id, name, presence, married)
                    list.add(guest)
                }
            }
            cursor?.close()

            list
        } catch (e: Exception) {
            list
        }
        list
    }

    fun getMarried(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getSingle(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //CRUD - create, read, update, delete

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.MARRIED, guest.married)

            //criterio de atualização
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            //criterio de atualização
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }
}