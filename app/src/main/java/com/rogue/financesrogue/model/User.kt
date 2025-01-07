package com.rogue.financesrogue.model

data class User (
    val userId: Int,
    val username: String,
    val password: String,
    var salary: Double
)