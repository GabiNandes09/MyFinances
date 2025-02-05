package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.PersonEntity

@Dao
interface PersonDAO {
    @Insert
    fun insertPerson(person: PersonEntity)

    @Delete
    fun deletePerson(person: PersonEntity)

    @Query("SELECT * FROM PersonEntity")
    fun selectAllPerson(): kotlinx.coroutines.flow.Flow<List<PersonEntity>>
}