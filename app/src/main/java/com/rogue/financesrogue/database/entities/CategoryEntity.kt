package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Int? = null,
    val category: String
){
    override fun toString(): String {
        return category
    }
}