package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Category
import com.rogue.financesrogue.model.ItemPurchased
import com.rogue.financesrogue.model.PaymentWay
import com.rogue.financesrogue.model.Person

class ItemPurchasedDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<ItemPurchased> = mutableListOf()
        val sql = "SELECT * " +
                "FROM ITEM_PURCHASED AS I " +
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
                    if (payforPerson > 0){
                        person = Person(
                            c.getInt(c.getColumnIndexOrThrow("PersonId")),
                            c.getString(c.getColumnIndexOrThrow("Person"))
                        )
                    }

                    val itemPurchased = ItemPurchased(
                        c.getInt(c.getColumnIndexOrThrow("ItemPurchasedId")),
                        c.getString(c.getColumnIndexOrThrow("dayOfPurchased")),
                        category,
                        c.getDouble(c.getColumnIndexOrThrow("Price")),
                        paymentWay,
                        c.getString(c.getColumnIndexOrThrow("Description")),
                        payforPerson > 0,
                        person
                    )
                    result.add(itemPurchased)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos os itens comprados ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * " +
                "FROM ITEM_PURCHASED AS I " +
                "INNER JOIN CATEGORY AS C ON I.idCategory = C.CategoryId " +
                "INNER JOIN PAYMENT_WAY AS P ON I.idPaymentWay = P.PaymentWayId " +
                "INNER JOIN PERSON ON I.idPerson = PERSON.PersonId " +
                "WHERE ItemPurchasedId = ?;"


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
                    var person: Person? = null
                    if (payforPerson > 0){
                        person = Person(
                            c.getInt(c.getColumnIndexOrThrow("PersonId")),
                            c.getString(c.getColumnIndexOrThrow("Person"))
                        )
                    }

                    return ItemPurchased(
                        c.getInt(c.getColumnIndexOrThrow("ItemPurchasedId")),
                        c.getString(c.getColumnIndexOrThrow("dayOfPurchased")),
                        category,
                        c.getDouble(c.getColumnIndexOrThrow("Price")),
                        paymentWay,
                        c.getString(c.getColumnIndexOrThrow("Description")),
                        payforPerson > 0,
                        person
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar Item comprado com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val itemPurchased = any as ItemPurchased
        val values = ContentValues().apply {
            put("dayOfPurchased", itemPurchased.dayOfPurchased)
            put("idCategory", itemPurchased.category.categoryId)
            put("Price", itemPurchased.price)
            put("idPaymentWay", itemPurchased.paymentWay.paymentWayId)
            put("Description", itemPurchased.description)
            put("PayForPerson", if (itemPurchased.payForPerson) 1 else 0)
            put("idPerson", itemPurchased.person?.personId)
        }

        db.writableDatabase.insert("ITEM_PURCHASED", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Item comprado ${itemPurchased.description} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${itemPurchased.description}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }
}