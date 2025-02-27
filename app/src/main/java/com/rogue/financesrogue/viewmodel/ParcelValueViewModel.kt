package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.ParcelValuesDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.ParcelValueEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParcelValueViewModel(
    private val parcelValueRepository: ParcelValuesDAO,
    private val categoryRepository: CategoryDAO,
    private val personRepository: PersonDAO
) : ViewModel() {
    private val _categoryList = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categoryList = _categoryList.asStateFlow()

    private val _personList = MutableStateFlow<List<PersonEntity>>(emptyList())
    val personList = _personList.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    private val _parcels = MutableStateFlow(0)
    val parcels = _parcels.asStateFlow()

    private val _parcelPrice = MutableStateFlow(0.0)
    val parcelPrice = _parcelPrice.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _payForPerson = MutableStateFlow(false)
    val payForPerson = _payForPerson.asStateFlow()

    private val _categorySelected = MutableStateFlow<CategoryEntity?>(null)

    private val _personSelected = MutableStateFlow<PersonEntity?>(null)

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _errorLog = MutableStateFlow("")
    val errorLog = _errorLog.asStateFlow()

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
        _totalPrice.value = price
        _parcelPrice.value = (price/_parcels.value)
    }

    fun setParcels(parcels: Int) {
        _parcels.value = parcels
        _parcelPrice.value = _totalPrice.value/parcels
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
        if (_categorySelected.value == null ||
            _totalPrice.value <= 0.0 ||
            _parcels.value <= 0 ||
            _description.value.isEmpty() || (
                    _payForPerson.value && _personSelected.value == null
                    )
            ){
            _errorLog.value = "Todos os campos devem ser preenchidos"
            _hasError.value = true
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val parcelValue = ParcelValueEntity(
                totalValue = _totalPrice.value,
                idCategory = _categorySelected.value?.categoryId!!,
                parcels = _parcels.value,
                parcelPrice = _parcelPrice.value,
                description = _description.value,
                payForPerson = _payForPerson.value,
                idPerson = _personSelected.value?.personId
            )

            parcelValueRepository.inserParcelValue(parcelValue)

            withContext(Dispatchers.Main){
                Nav.navController?.popBackStack()
            }
        }

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

    fun resetErrors(){
        _hasError.value = false
        _errorLog.value = ""
    }
}