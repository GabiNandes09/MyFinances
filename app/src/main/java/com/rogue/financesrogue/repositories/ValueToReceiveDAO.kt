package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.ItemPurchased
import com.rogue.financesrogue.model.PaymentWay
import com.rogue.financesrogue.model.Person
import com.rogue.financesrogue.model.ValueToReceive
import com.rogue.financesrogue.model.ValueType

class ValueToReceiveDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<ValueToReceive> = mutableListOf()
        val sql = "SELECT * " +
                "FROM VALUE_TO_RECEIVE AS I " +
                "INNER JOIN PAYEMNT_WAY AS P ON I.IdPaymentWay = P.PaymentWayId " +
                "INNER JOIN PERSON ON I.idPerson = PERSON.PersonId " +
                ";"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val paymentWay = PaymentWay(
                        c.getInt(c.getColumnIndexOrThrow("PaymentWayId")),
                        c.getString(c.getColumnIndexOrThrow("PaymentWay"))
                    )
                    val person = Person(
                        c.getInt(c.getColumnIndexOrThrow("PersonId")),
                        c.getString(c.getColumnIndexOrThrow("Person"))
                    )

                    val typeNumber = c.getInt(c.getColumnIndexOrThrow("Type"))
                    val type = when (typeNumber) {
                        0 -> ValueType.UNIQUE
                        1 -> ValueType.FIXED
                        2 -> ValueType.PARCEL
                        else -> ValueType.UNIQUE
                    }

                    val valueToReceive = ValueToReceive(
                        c.getInt(c.getColumnIndexOrThrow("ValueToReceiveId")),
                        person,
                        c.getDouble(c.getColumnIndexOrThrow("TotalPrice")),
                        paymentWay,
                        type,
                        c.getInt(c.getColumnIndexOrThrow("Parcels")),
                        c.getString(c.getColumnIndexOrThrow("Description"))
                    )
                    result.add(valueToReceive)
                }
            }
        } catch (e: Exception) {
            Log.i(
                "Banco de dados",
                "Erro ao buscar todos os valores a receber ${e.printStackTrace()}"
            )
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * " +
                "FROM VALUE_TO_RECEIVE AS I " +
                "INNER JOIN PAYMENT_WAY AS P ON I.idPaymentWay = P.PaymentWayId " +
                "INNER JOIN PERSON ON I.idPerson = PERSON.PersonId " +
                "WHERE ValueToReceiveId = ?;"


        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    val paymentWay = PaymentWay(
                        c.getInt(c.getColumnIndexOrThrow("PaymentWayId")),
                        c.getString(c.getColumnIndexOrThrow("PaymentWay"))
                    )
                    val person = Person(
                        c.getInt(c.getColumnIndexOrThrow("PersonId")),
                        c.getString(c.getColumnIndexOrThrow("Person"))
                    )

                    val typeNumber = c.getInt(c.getColumnIndexOrThrow("Type"))
                    val type = when (typeNumber) {
                        0 -> ValueType.UNIQUE
                        1 -> ValueType.FIXED
                        2 -> ValueType.PARCEL
                        else -> ValueType.UNIQUE
                    }

                    return ValueToReceive(
                        c.getInt(c.getColumnIndexOrThrow("ValueToReceiveId")),
                        person,
                        c.getDouble(c.getColumnIndexOrThrow("TotalPrice")),
                        paymentWay,
                        type,
                        c.getInt(c.getColumnIndexOrThrow("Parcels")),
                        c.getString(c.getColumnIndexOrThrow("Description"))
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar valor a receber com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val valueToReceive = any as ValueToReceive
        val values = ContentValues().apply {
            put("PersonToReceive", valueToReceive.personToReceive.personId)
            put("TotalPrice", valueToReceive.totalPrice)
            put("idPaymentWay", valueToReceive.paymentWay.paymentWayId)
            put("Type", valueToReceive.type.type)
            put("Parcels", valueToReceive.parcels)
            put("Description", valueToReceive.description)
        }

        db.writableDatabase.insert("VALUE_TO_RECEIVE", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Valor a receber ${valueToReceive.description} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${valueToReceive.description}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }


}