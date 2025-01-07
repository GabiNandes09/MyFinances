package com.rogue.financesrogue.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.IOException

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, "loja.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        executeSqlScript(db, "create_tables.sql")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        for (version in (oldVersion + 1)..newVersion) {
            val fileName = "upgrade_v${version - 1}_to_v$version.sql"
            executeSqlScript(db, fileName)
        }
    }

    private fun executeSqlScript(db: SQLiteDatabase?, fileName: String) {
        try {
            val inputStream = context.assets.open(fileName)
            val reader = inputStream.bufferedReader()
            val sqlStatements = reader.use { it.readText() }
            for (sql in sqlStatements.split(";")) {
                if (sql.trim().isNotEmpty()) {
                    db?.execSQL(sql.trim())
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException("Erro ao executar o script $fileName: ${e.message}")
        }
    }
}