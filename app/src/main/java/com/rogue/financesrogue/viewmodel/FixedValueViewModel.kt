package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.FixedValueDAO
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.FixedValueEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _errorLog = MutableStateFlow("")
    val errorLog = _errorLog.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { loadCategory() }
            launch { loadPaymentWay() }
            launch { loadPerson() }
        }
    }

    private suspend fun loadCategory() {
        categoryRepository.selectAllCategory().collect { categorylist ->
            _categoryList.value = categorylist
        }
    }

    private suspend fun loadPaymentWay() {
        paymentWayRepository.selectAllPaymentWay().collect { list ->
            _paymentWayList.value = list
        }
    }

    private suspend fun loadPerson() {
        personRepository.selectAllPerson().collect { list ->
            _personList.value = list
        }
    }

    fun setCategory(category: CategoryEntity) {
        _categorySelected.value = category
    }

    fun setPaymentWay(paymentWay: PaymentWayEntity) {
        _paymentWaySelected.value = paymentWay
    }

    fun setPerson(person: PersonEntity) {
        _personSelected.value = person
    }

    fun setIsVariable(isVariable: Boolean) {
        _isVariable.value = isVariable
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setPrice(price: Double) {
        _price.value = price
    }

    fun onAddCategory(string: String) {
        if (_categoryList.value.find { it.category.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.insertCategory(CategoryEntity(category = string.uppercase()))
        }
    }

    fun onAddPaymentWay(string: String) {
        if (_paymentWayList.value.find { it.paymentWay.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            paymentWayRepository.insertPaymentWay(PaymentWayEntity(paymentWay = string))
        }
    }

    fun onAddPerson(string: String) {
        if (_personList.value.find { it.person.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(PersonEntity(person = string))
        }
    }

    fun resetErrors() {
        _hasError.value = false
        _errorLog.value = ""
    }

    fun saveFixedValue() {
        if (_categorySelected.value == null ||
            _price.value <= 0.0 ||
            _description.value.isEmpty() ||
            _paymentWaySelected.value == null
        ) {
            _errorLog.value = "Todos os campos devem ser preenchidos"
            _hasError.value = true
            return
        }

        val payForPerson = _paymentWaySelected.value!!.paymentWay.equals(
            "Pessoa",
            ignoreCase = true
        )

        if (payForPerson && _personSelected.value == null) {
            _errorLog.value = "Selecione a pessoa referente ao pagamento"
            _hasError.value = true
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val fixedValue = FixedValueEntity(
                idCategory = _categorySelected.value!!.categoryId!!,
                price = _price.value,
                idPaymentWay = _paymentWaySelected.value!!.paymentWayId!!,
                payForPerson = payForPerson,
                variable = _isVariable.value,
                description = _description.value,
                idPerson = _personSelected.value?.personId
            )

            fixedValueRepository.insertFixedValue(fixedValue)

            withContext(Dispatchers.Main){
                Nav.navController?.popBackStack()
            }
        }


    }
}