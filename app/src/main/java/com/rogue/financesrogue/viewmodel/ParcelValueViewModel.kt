package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.ParcelValuesDAO
import com.rogue.financesrogue.database.dao.PersonDAO

class ParcelValueViewModel(
    private val parvelValueRepository: ParcelValuesDAO,
    private val categoryRepository: CategoryDAO,
    private val personRepository: PersonDAO
) : ViewModel() {
}