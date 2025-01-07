package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Brand

class BrandDAO(context: Context) : IRepository {
    val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<Brand> = mutableListOf()
        val sql = "SELECT * FROM BRAND;"
        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val brand = Brand(
                        c.getInt(c.getColumnIndexOrThrow("BrandId")),
                        c.getString(c.getColumnIndexOrThrow("Brand"))
                    )
                    result.add(brand)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos as marcas ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * FROM BRAND WHERE BrandId = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return Brand(
                        c.getInt(c.getColumnIndexOrThrow("BrandId")),
                        c.getString(c.getColumnIndexOrThrow("Brand"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Marca com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val brand = any as Brand
        val values = ContentValues().apply {
            put("Brand", brand.brand)
        }

        db.writableDatabase.insert("BRAND", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Marca ${brand.brand} inserida com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${brand.brand}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}