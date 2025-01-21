package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val username: String,
    val password: String,
    var salary: Double? = 0.0
)
