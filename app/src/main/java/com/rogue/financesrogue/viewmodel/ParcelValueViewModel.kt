package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.ParcelValuesDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ParcelValueViewModel(
    private val parcelValueRepository: ParcelValuesDAO,
    private val categoryRepository: CategoryDAO,
    private val personRepository: PersonDAO
) : ViewModel() {
    private val _categoryList = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categoryList = _categoryList.asStateFlow()

    private val _personList = MutableStateFlow<List<PersonEntity>>(emptyList())
    val personList = _personList.asStateFlow()

    private val _price = MutableStateFlow(0.0)
    val price = _price.asStateFlow()

    private val _parcels = MutableStateFlow(0)
    val parcels = _parcels.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _payForPerson = MutableStateFlow(false)
    val payForPerson = _payForPerson.asStateFlow()

    private val _categorySelected = MutableStateFlow<CategoryEntity?>(null)
    val categorySelected = _categorySelected.asStateFlow()

    private val _personSelected = MutableStateFlow<PersonEntity?>(null)
    val personSelected = _personSelected.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { loadCategory() }
            launch { loadPerson() }
        }
    }

    private suspend fun loadCategory() {
        categoryRepository.selectAllCategory().collect { list ->
            _categoryList.value = list
        }
    }

    private suspend fun loadPerson() {
        personRepository.selectAllPerson().collect { list ->
            _personList.value = list
        }
    }

    fun setPrice(price: Double) {
        _price.value = price
    }

    fun setParcels(parcels: Int) {
        _parcels.value = parcels
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setPayForPerson(pay: Boolean) {
        _payForPerson.value = pay
    }

    fun setCategory(category: CategoryEntity) {
        _categorySelected.value = category
    }

    fun setPerson(person: PersonEntity) {
        _personSelected.value = person
    }

    fun saveParcelValue() {

    }

    fun onAddCategory(string: String){
        if (_categoryList.value.find { it.category.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.insertCategory(CategoryEntity(category = string.uppercase()))
        }
    }

    fun addPerson(string: String){
        if (_personList.value.find { it.person.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(PersonEntity(person = string))
        }
    }
}