package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Category

class CategoryDao(context: Context) : IRepository {
    val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<Category> = mutableListOf()
        val sql = "SELECT * FROM CATEGORY;"
        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val category = Category(
                        c.getInt(c.getColumnIndexOrThrow("CategoryId")),
                        c.getString(c.getColumnIndexOrThrow("Category"))
                    )
                    result.add(category)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todas as categorias ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * FROM CATEGORY WHERE CategoryId = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return Category(
                        c.getInt(c.getColumnIndexOrThrow("CategoryId")),
                        c.getString(c.getColumnIndexOrThrow("Category"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Category com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val category = any as Category
        val values = ContentValues().apply {
            put("Category", category.category)
        }

        db.writableDatabase.insert("CATEGORY", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Categoria ${category.category} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${category.category}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}