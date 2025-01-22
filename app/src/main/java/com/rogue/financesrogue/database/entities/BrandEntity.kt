package com.rogue.financesrogue.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BrandEntity(
    @PrimaryKey(autoGenerate = true)
    val brandId: Int,
    val brand: String
)