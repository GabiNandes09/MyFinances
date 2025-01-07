package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.PaymentWay

class PaymentWayDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<PaymentWay> = mutableListOf()
        val sql = "SELECT * FROM PAYMENT_WAY;"
        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val paymentWay = PaymentWay(
                        c.getInt(c.getColumnIndexOrThrow("PaymentWayId")),
                        c.getString(c.getColumnIndexOrThrow("PaymentWay"))
                    )
                    result.add(paymentWay)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos os Métodos de pagamento ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * FROM PAYMENT_WAY WHERE PaymentWayId = ?;"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    return PaymentWay(
                        c.getInt(c.getColumnIndexOrThrow("PaymentWayId")),
                        c.getString(c.getColumnIndexOrThrow("PaymentWay"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Método de pagamento com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val paymentWay = any as PaymentWay
        val values = ContentValues().apply {
            put("PaymentWay", paymentWay.paymentWay)
        }

        db.writableDatabase.insert("PAYMENT_WAY", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Método de pagamento ${paymentWay.paymentWay} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${paymentWay.paymentWay}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}