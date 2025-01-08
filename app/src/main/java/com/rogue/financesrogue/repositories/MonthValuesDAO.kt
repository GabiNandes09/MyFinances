package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.ItemPurchased
import com.rogue.financesrogue.model.MonthValues

class MonthValuesDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<MonthValues> = mutableListOf()
        val sql = "SELECT * " +
                "FROM MONTH_VALUES " +
                ";"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val monthValues = MonthValues(
                        c.getInt(c.getColumnIndexOrThrow("Month")),
                        c.getInt(c.getColumnIndexOrThrow("Year")),
                        c.getDouble(c.getColumnIndexOrThrow("TotalToPay")),
                        c.getDouble(c.getColumnIndexOrThrow("TotalParcel"))
                    )
                    result.add(monthValues)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos os valores mensais ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * " +
                "FROM MONTH_VALUES AS I " +
                "WHERE Month = ? AND Year = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return MonthValues(
                        c.getInt(c.getColumnIndexOrThrow("Month")),
                        c.getInt(c.getColumnIndexOrThrow("Year")),
                        c.getDouble(c.getColumnIndexOrThrow("TotalToPay")),
                        c.getDouble(c.getColumnIndexOrThrow("TotalParcel"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar valor mensal com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val monthValue = any as MonthValues
        val values = ContentValues().apply {
            put("Month", monthValue.month)
            put("Year", monthValue.year)
            put("TotalToPay", monthValue.totalToPay)
            put("TotalParcel", monthValue.totalParcel)
        }

        db.writableDatabase.insert("MONTH_VALUES", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Valor mensal ${monthValue.month}/${monthValue.year} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${monthValue.month}/${monthValue.year}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}