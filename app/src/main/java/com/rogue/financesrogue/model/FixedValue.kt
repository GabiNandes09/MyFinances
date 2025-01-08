package com.rogue.financesrogue.model

data class FixedValue(
    val fixedValuesId: Int,
    val category: Category,
    val price: Double,
    val paymentWay: PaymentWay,
    val payForPerson: Boolean,
    val variable: Boolean,
    val description: String,
    val person: Person?
)
