package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.User

class UserDAO(context: Context) : IRepository {

    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<User> = mutableListOf()
        val sql = "SELECT * FROM USER;"
        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val user = User(
                        c.getInt(c.getColumnIndexOrThrow("UserId")),
                        c.getString(c.getColumnIndexOrThrow("Username")),
                        c.getString(c.getColumnIndexOrThrow("Password")),
                        c.getDouble(c.getColumnIndexOrThrow("Salary"))
                    )
                    result.add(user)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos os usuários ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * FROM USER WHERE UserId = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return User(
                        c.getInt(c.getColumnIndexOrThrow("UserId")),
                        c.getString(c.getColumnIndexOrThrow("Username")),
                        c.getString(c.getColumnIndexOrThrow("Password")),
                        c.getDouble(c.getColumnIndexOrThrow("Salary"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar User com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val user = any as User
        val values = ContentValues().apply {
            put("Username", user.username)
            put("Password", user.password)
            put("salary", user.salary)
        }

        db.writableDatabase.insert("USER", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Usuário ${user.username} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${user.username}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}