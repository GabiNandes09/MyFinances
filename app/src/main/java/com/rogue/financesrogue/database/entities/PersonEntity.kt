package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonEntity (
    @PrimaryKey(autoGenerate = true)
    val personId: Int? = null,
    val person: String
)