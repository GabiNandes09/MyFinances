package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Brand
import com.rogue.financesrogue.model.CreditCard

class CreditCardDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<CreditCard> = mutableListOf()
        val sql = "SELECT C.CreditCardId, C.Name, C.ClosingDay, B.BrandId, B.Brand " +
                "FROM CREDIT_CARD AS C " +
                "INNER JOIN BRAND AS B ON C.idBrand = B.BrandId" +
                ";"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val creditCard = CreditCard(
                        c.getInt(c.getColumnIndexOrThrow("CreditCardId")),
                        Brand(
                            c.getInt(c.getColumnIndexOrThrow("BrandId")),
                            c.getString(c.getColumnIndexOrThrow("Brand"))
                        ),
                        c.getString(c.getColumnIndexOrThrow("Name")),
                        c.getInt(c.getColumnIndexOrThrow("ClosingDay"))
                    )
                    result.add(creditCard)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos Cartões de crédito ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT C.CreditCardId, C.Name, C.ClosingDay, B.BrandId, B.Brand " +
                "FROM CREDIT_CARD AS C " +
                "INNER JOIN BRAND AS B ON C.idBrand = B.BrandId " +
                "WHERE C.CreditCardId = ?" +
                ";"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return CreditCard(
                        c.getInt(c.getColumnIndexOrThrow("CreditCardId")),
                        Brand(
                            c.getInt(c.getColumnIndexOrThrow("BrandId")),
                            c.getString(c.getColumnIndexOrThrow("Brand"))
                        ),
                        c.getString(c.getColumnIndexOrThrow("Name")),
                        c.getInt(c.getColumnIndexOrThrow("ClosingDay"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Cartão de crédito com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val creditCard = any as CreditCard
        val values = ContentValues().apply {
            put("idBrand", creditCard.brand.brandId)
            put("Name", creditCard.name)
            put("ClosingDay", creditCard.closingDay)
        }

        db.writableDatabase.insert("CREDIT_CARD", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Cartão de crédito ${creditCard.name} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${creditCard.name}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}