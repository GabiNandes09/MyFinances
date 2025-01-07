package com.rogue.financesrogue.repositories

import android.content.Context
import android.database.Cursor
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
                while (c.moveToNext()){
                    val user = User(
                        c.getInt(c.getColumnIndexOrThrow("UserId")),
                        c.getString(c.getColumnIndexOrThrow("Username")),
                        c.getString(c.getColumnIndexOrThrow("Password")),
                        c.getDouble(c.getColumnIndexOrThrow("Salary"))
                    )
                    result.add(user)
                }
            }
        } catch (e: Exception){
            Log.i("Banco de dados", "Erro ao buscar todos os usuários ${e.printStackTrace()}")
        } finally {
            
        }
        return result
    }

    override fun selectById(id: Int): Any {
        val sql = "SELECT * FROM USER WHERE UserId = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            if (cursor != null){
                return User(
                    cursor.getInt(cursor.getColumnIndexOrThrow("UserId")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Password")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("Salary"))
                )
            }
        }
    }

    override fun insertOne(any: Any) {
        TODO("Not yet implemented")
    }

    override fun deleteOne(id: Int) {
        TODO("Not yet implemented")
    }
}