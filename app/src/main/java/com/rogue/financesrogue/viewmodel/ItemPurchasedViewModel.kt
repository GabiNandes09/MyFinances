package com.rogue.financesrogue.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.ItemPurchasedDAO
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ItemPurchasedViewModel(
    private val itemRepository: ItemPurchasedDAO,
    private val paymentWayRepository: PaymentWayDAO,
    private val categoryRepository: CategoryDAO,
    private val personRepository: PersonDAO
) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    private val _paymentWayList = MutableStateFlow<List<PaymentWayEntity>>(emptyList())
    val paymentWayList = _paymentWayList.asStateFlow()

    private val _categoryList = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categoryList = _categoryList.asStateFlow()

    private val _personList = MutableStateFlow<List<PersonEntity>>(emptyList())
    val personList = _personList.asStateFlow()

    private val _category = MutableStateFlow<CategoryEntity?>(null)
    val category = _category.asStateFlow()

    private val _value = MutableStateFlow<Double>(0.0)
    val value = _value.asStateFlow()

    private val _description = MutableStateFlow<String>("")
    val description = _description.asStateFlow()

    private val _date = MutableStateFlow<LocalDate?>(null)
    val date = _date.asStateFlow()

    private val _paymentWay = MutableStateFlow<PaymentWayEntity?>(null)
    val paymentWay = _paymentWay.asStateFlow()

    private val _person = MutableStateFlow<PersonEntity?>(null)
    val person = _person.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { loadPaymentWay() }
            launch { loadCategory() }
            launch { loadPerson() }
        }
    }

    private suspend fun loadPaymentWay() {
        paymentWayRepository.selectAllPaymentWay().collect{pwList ->
            _paymentWayList.value = pwList
        }
    }

    private suspend fun loadCategory() {
        categoryRepository.selectAllCategory().collect{categorylist ->
            _categoryList.value = categorylist
        }
    }

    private suspend fun loadPerson() {
        personRepository.selectAllPerson().collect{personList ->
            _personList.value = personList
        }
    }

    fun setCategory(category: CategoryEntity){
        _category.value = category
    }

    fun setValue(value: Double){
        _value.value = value
    }

    fun setDescription(description: String){
        _description.value = description
    }

    fun setDate(date: LocalDate){
        _date.value = date
    }

    fun setPaymentWay(pw: PaymentWayEntity){
        _paymentWay.value = pw
    }

    fun setPerson(person: PersonEntity){
        _person.value = person
    }

    fun errorOkay(){
        _hasError.value = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveItem(){
        if (checkEverything()){
            viewModelScope.launch(Dispatchers.IO) {
                itemRepository.insertItemPurchased(
                    ItemPurchasedEntity(
                        dayOfPurchased = _date.value?.format(formatter) ?: "Erro",
                        idCategory = _category.value?.categoryId ?: -1,
                        price = _value.value,
                        idPaymentWay = _paymentWay.value?.paymentWayId ?: -1,
                        description = _description.value,
                        payForPerson = (_paymentWay.value?.paymentWay.equals("pessoa", ignoreCase = true)),
                        idPerson = _person.value?.personId
                    )
                )
                withContext(Dispatchers.Main){
                    Nav.navController?.popBackStack()
                }
            }
        } else {
            _hasError.value = true
        }
    }

    private fun checkEverything(): Boolean{
        if (
            _category.value != null &&
            _value.value > 0.0 &&
            _description.value.isNotEmpty() &&
            _date.value != null &&
            _paymentWay.value != null
        ){
            return !(_paymentWay.value?.paymentWay.equals("Pessoa", ignoreCase = true) && _person.value == null)
        }
        return false
    }

    fun onAddCategory(string: String){
        if (_categoryList.value.find { it.category.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.insertCategory(CategoryEntity(category = string.uppercase()))
        }
    }

    fun onAddPaymentWay(string: String){
        if (_paymentWayList.value.find { it.paymentWay.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            paymentWayRepository.insertPaymentWay(PaymentWayEntity(paymentWay = string))
        }
    }

    fun onAddPerson(string: String){
        if (_personList.value.find { it.person.equals(string, ignoreCase = true) } != null) return

        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(PersonEntity(person = string))
        }
    }
}