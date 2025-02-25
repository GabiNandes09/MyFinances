package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.dao.ValueToReceiveDAO
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.database.entities.ValueToReceiveEntity
import com.rogue.financesrogue.model.ValueType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ValueToReceiveViewModel(
    private val personRepository: PersonDAO,
    private val valueToReceiveRepository: ValueToReceiveDAO
) : ViewModel() {
    private val _personList = MutableStateFlow<List<PersonEntity>>(emptyList())
    val personList = _personList.asStateFlow()

    private val _person = MutableStateFlow<PersonEntity?>(null)

    private val _price = MutableStateFlow(0.0)
    val price = _price.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _parcels = MutableStateFlow<Int?>(null)
    val parcels = _parcels.asStateFlow()

    private val _type = MutableStateFlow<ValueType?>(null)
    val type = _type.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _errorLog = MutableStateFlow("")
    val errorLog = _errorLog.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { loadPersonList() }
        }
    }

    fun setPrice(price: Double) {
        _price.value = price
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setParcels(parcels: Int?) {
        _parcels.value = parcels
    }

    fun setType(type: ValueType) {
        _type.value = type
    }

    fun setPerson(person: PersonEntity) {
        _person.value = person
    }

    fun resetError() {
        _hasError.value = false
        _errorLog.value = ""
    }

    fun onAddPerson(person: String) {
        val exists = _personList.value.find { it.person.equals(person, ignoreCase = true) }
        if (exists == null) {
            viewModelScope.launch(Dispatchers.IO) {
                personRepository.insertPerson(PersonEntity(person = person))
            }
        }
    }

    private suspend fun loadPersonList() {
        personRepository.selectAllPerson().collect { personList ->
            _personList.value = personList
        }
    }

    fun saveValueToReceive() {
        if (_person.value == null ||
            _price.value <= 0 ||
            _type.value == null ||
            _description.value.isEmpty()
        ) {
            _errorLog.value = "Todos os campos devem ser preenchidos"
            _hasError.value = true
            return
        }

        val typeNumber = _type.value!!.type

        if (typeNumber != 2) {
            _parcels.value = 1
        } else if (_parcels.value == null || _parcels.value!! < 2) {
            _errorLog.value = "O número de parcelas deve condizer com o tipo escolhido"
            _hasError.value = true
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val value = ValueToReceiveEntity(
                personToReceive = _person.value!!.personId!!,
                totalPrice = _price.value,
                type = typeNumber,
                parcels = _parcels.value!!,
                description = _description.value
            )


            valueToReceiveRepository.insertValueToReceive(value)
            withContext(Dispatchers.Main) {
                Nav.navController?.popBackStack()
            }
        }
    }
}