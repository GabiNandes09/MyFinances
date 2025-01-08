package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Category
import com.rogue.financesrogue.model.FixedValue
import com.rogue.financesrogue.model.PaymentWay
import com.rogue.financesrogue.model.Person

class FixedValueDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<FixedValue> = mutableListOf()
        val sql = "SELECT * " +
                "FROM FIXED_VALUE AS I " +
                "INNER JOIN CATEGORY AS C ON I.idCategory = C.CategoryId " +
                "INNER JOIN PAYMENT_WAY AS P ON I.idPaymentWay = P.PaymentWayId " +
                "INNER JOIN PERSON ON I.idPerson = PERSON.PersonId" +
                ";"

        try {
            val cursor = db.readableDatabase.rawQuery(sql, null)
            cursor.use { c ->
                while (c.moveToNext()) {
                    val category = Category(
                        c.getInt(c.getColumnIndexOrThrow("CategoryId")),
                        c.getString(c.getColumnIndexOrThrow("Category"))
                    )
                    val paymentWay = PaymentWay(
                        c.getInt(c.getColumnIndexOrThrow("PaymentWayId")),
                        c.getString(c.getColumnIndexOrThrow("PaymentWay"))
                    )
                    var person: Person? = null
                    val payforPerson = c.getInt(c.getColumnIndexOrThrow("PayForPerson"))
                    val variable = c.getInt(c.getColumnIndexOrThrow("Variable"))
                    if (payforPerson > 0){
                        person = Person(
                            c.getInt(c.getColumnIndexOrThrow("PersonId")),
                            c.getString(c.getColumnIndexOrThrow("Person"))
                        )
                    }

                    val fixedValue = FixedValue(
                        c.getInt(c.getColumnIndexOrThrow("FixedValueId")),
                        category,
                        c.getDouble(c.getColumnIndexOrThrow("Price")),
                        paymentWay,
                        payforPerson > 0,
                        variable > 0,
                        c.getString(c.getColumnIndexOrThrow("Description")),
                        person
                    )
                    result.add(fixedValue)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos os valores fixos ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * " +
                "FROM FIXED_VALUE AS I " +
                "INNER JOIN CATEGORY AS C ON I.idCategory = C.CategoryId " +
                "INNER JOIN PAYMENT_WAY AS P ON I.idPaymentWay = P.PaymentWayId " +
                "INNER JOIN PERSON ON I.idPerson = PERSON.PersonId " +
                "WHERE FixedValueId = ?;"


        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {
                    val category = Category(
                        c.getInt(c.getColumnIndexOrThrow("CategoryId")),
                        c.getString(c.getColumnIndexOrThrow("Category"))
                    )
                    val paymentWay = PaymentWay(
                        c.getInt(c.getColumnIndexOrThrow("PaymentWayId")),
                        c.getString(c.getColumnIndexOrThrow("PaymentWay"))
                    )
                    val payforPerson = c.getInt(c.getColumnIndexOrThrow("PayForPerson"))
                    val variable = c.getInt(c.getColumnIndexOrThrow("Variable"))

                    var person: Person? = null
                    if (payforPerson > 0){
                        person = Person(
                            c.getInt(c.getColumnIndexOrThrow("PersonId")),
                            c.getString(c.getColumnIndexOrThrow("Person"))
                        )
                    }

                    return FixedValue(
                        c.getInt(c.getColumnIndexOrThrow("FixedValueId")),
                        category,
                        c.getDouble(c.getColumnIndexOrThrow("Price")),
                        paymentWay,
                        payforPerson > 0,
                        variable > 0,
                        c.getString(c.getColumnIndexOrThrow("Description")),
                        person
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Valor fixo com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val fixedValue = any as FixedValue
        val values = ContentValues().apply {
            put("idCategory", fixedValue.category.categoryId)
            put("Price", fixedValue.price)
            put("idPaymentWay", fixedValue.paymentWay.paymentWayId)
            put("PayForPerson", if (fixedValue.payForPerson) 1 else 0)
            put("Variable", if (fixedValue.variable) 1 else 0)
            put("Description", fixedValue.description)
            put("idPerson", fixedValue.person?.personId)
        }

        db.writableDatabase.insert("FIXED_VALUE", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Valor fixo ${fixedValue.description} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${fixedValue.description}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}