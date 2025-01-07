package com.rogue.financesrogue.interfaces

interface IRepository {
    fun selectAll() : List<Any>
    fun selectById(id: Int) : Any?
    fun insertOne (any: Any)
    fun deleteOneById (id: Int)
}