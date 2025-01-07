package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Person

class PersonDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<Person> = mutableListOf()
        val sql = "SELECT * FROM PERSON;"
        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val person = Person(
                        c.getInt(c.getColumnIndexOrThrow("PersonId")),
                        c.getString(c.getColumnIndexOrThrow("Person"))
                    )
                    result.add(person)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos as pessoas ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * FROM PERSON WHERE PersonId = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return Person(
                        c.getInt(c.getColumnIndexOrThrow("PersonId")),
                        c.getString(c.getColumnIndexOrThrow("Person"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Pessoa com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val person = any as Person
        val values = ContentValues().apply {
            put("Person", person.person)
        }

        db.writableDatabase.insert("PERSON", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Pessoa ${person.person} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${person.person}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}