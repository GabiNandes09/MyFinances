package com.rogue.financesrogue.repositories

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rogue.financesrogue.database.DatabaseHelper
import com.rogue.financesrogue.interfaces.IRepository
import com.rogue.financesrogue.model.Category
import com.rogue.financesrogue.model.ParcelValue
import com.rogue.financesrogue.model.Person

class ParcelValueDAO(context: Context) : IRepository {
    private val db = DatabaseHelper(context)

    override fun selectAll(): List<Any> {
        val result: MutableList<ParcelValue> = mutableListOf()
        val sql = "SELECT * " +
                "FROM PARCEL_VALUES AS I " +
                "INNER JOIN CATEGORY AS C ON I.idCategory = C.CategoryId " +
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

                    var person: Person? = null
                    val payforPerson = c.getInt(c.getColumnIndexOrThrow("PayForPerson"))

                    if (payforPerson > 0){
                        person = Person(
                            c.getInt(c.getColumnIndexOrThrow("PersonId")),
                            c.getString(c.getColumnIndexOrThrow("Person"))
                        )
                    }

                    val parcelValue = ParcelValue(
                        c.getInt(c.getColumnIndexOrThrow("ParcelValuesId")),
                        c.getDouble(c.getColumnIndexOrThrow("TotalValue")),
                        category,
                        c.getInt(c.getColumnIndexOrThrow("Parcels")),
                        c.getString(c.getColumnIndexOrThrow("Description")),
                        payforPerson > 0,
                        person
                    )
                    result.add(parcelValue)
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar todos os valores parcelados ${e.printStackTrace()}")
        }
        return result
    }

    override fun selectById(id: Int): Any? {
        val sql = "SELECT * " +
                "FROM PARCEL_VALUES AS I " +
                "INNER JOIN CATEGORY AS C ON I.idCategory = C.CategoryId " +
                "INNER JOIN PERSON ON I.idPerson = PERSON.PersonId " +
                "WHERE ParcelValuesId = ?;"


        try {
            val cursor = db.readableDatabase.rawQuery(sql, arrayOf(id.toString()))
            cursor.use { c ->
                if (c != null) {val category = Category(
                    c.getInt(c.getColumnIndexOrThrow("CategoryId")),
                    c.getString(c.getColumnIndexOrThrow("Category"))
                )

                    var person: Person? = null
                    val payforPerson = c.getInt(c.getColumnIndexOrThrow("PayForPerson"))

                    if (payforPerson > 0){
                        person = Person(
                            c.getInt(c.getColumnIndexOrThrow("PersonId")),
                            c.getString(c.getColumnIndexOrThrow("Person"))
                        )
                    }

                    return ParcelValue(
                        c.getInt(c.getColumnIndexOrThrow("ParcelValuesId")),
                        c.getDouble(c.getColumnIndexOrThrow("TotalValue")),
                        category,
                        c.getInt(c.getColumnIndexOrThrow("Parcels")),
                        c.getString(c.getColumnIndexOrThrow("Description")),
                        payforPerson > 0,
                        person
                    )
                }
            }
        } catch (e: Exception) {
            Log.i("Banco de dados", "Erro ao buscar valor parcelado com ID $id")
        }
        return null
    }

    override fun insertOne(any: Any) {
        val parcelValue = any as ParcelValue
        val values = ContentValues().apply {
            put("TotalValue", parcelValue.totalValue)
            put("idCategory", parcelValue.category.categoryId)
            put("Parcels", parcelValue.parcels)
            put("Description", parcelValue.description)
            put("PayForPerson", if (parcelValue.payForPerson) 1 else 0)
            put("idPerson", parcelValue.person?.personId)
        }

        db.writableDatabase.insert("PARCEL_VALUES", null, values).also {
            if (it >= 0) {
                Log.i("Banco de dados", "Valor parcelado ${parcelValue.description} inserido com sucesso!")
            } else {
                Log.i("Banco de dados", "Erro ao inserir ${parcelValue.description}")
            }
        }
    }

    override fun deleteOneById(id: Int) {
        TODO("Not yet implemented")
    }


}