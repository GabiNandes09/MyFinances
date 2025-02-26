package com.rogue.financesrogue.viewmodel

import android.credentials.CredentialDescription
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.FixedValueDAO
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FixedValueViewModel(
    private val fixedValueRepository: FixedValueDAO,
    private val categoryRepository: CategoryDAO,
    private val paymentWayRepository: PaymentWayDAO,
    private val personRepository: PersonDAO
) : ViewModel() {
    private val _categoryList = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categoryList = _categoryList.asStateFlow()

    private val _paymentWayList = MutableStateFlow<List<PaymentWayEntity>>(emptyList())
    val paymentWayList = _paymentWayList.asStateFlow()

    private val _personList = MutableStateFlow<List<PersonEntity>>(emptyList())
    val personList = _personList.asStateFlow()

    private val _paymentWaySelected = MutableStateFlow<PaymentWayEntity?>(null)
    val paymentWaySelected = _paymentWaySelected.asStateFlow()

    private val _categorySelected = MutableStateFlow<CategoryEntity?>(null)
    val categorySelected = _categorySelected.asStateFlow()

    private val _personSelected = MutableStateFlow<PersonEntity?>(null)
    val personSelected = _personSelected.asStateFlow()

    private val _isVariable = MutableStateFlow(false)
    val isVariable = _isVariable.asStateFlow()

    private val _price = MutableStateFlow(0.0)
    val price = _price.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { loadCategory() }
            launch { loadPaymentWay() }
            launch { loadPerson() }
        }
    }

    private suspend fun loadCategory(){
        categoryRepository.selectAllCategory().collect{categorylist ->
            _categoryList.value = categorylist
        }
    }

    private suspend fun loadPaymentWay(){
        paymentWayRepository.selectAllPaymentWay().collect{list ->
            _paymentWayList.value = list
        }
    }

    private suspend fun loadPerson(){
        personRepository.selectAllPerson().collect{list ->
            _personList.value = list
        }
    }

    fun setCategory(category: CategoryEntity){
        _categorySelected.value = category
    }

    fun setPaymentWay(paymentWay: PaymentWayEntity){
        _paymentWaySelected.value = paymentWay
    }

    fun setPerson(person: PersonEntity){
        _personSelected.value = person
    }

    fun setIsVariable(isVariable: Boolean){
        _isVariable.value = isVariable
    }

    fun setDescription(description: String){
        _description.value = description
    }

    fun setPrice(price: Double){
        _price.value = price
    }

    fun onAddCategory(string: String){
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.insertCategory(CategoryEntity(category = string.uppercase()))
        }
    }

    fun addPaymentWay(string: String){
        viewModelScope.launch(Dispatchers.IO) {
            paymentWayRepository.insertPaymentWay(PaymentWayEntity(paymentWay = string))
        }
    }

    fun addPerson(string: String){
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(PersonEntity(person = string))
        }
    }
}