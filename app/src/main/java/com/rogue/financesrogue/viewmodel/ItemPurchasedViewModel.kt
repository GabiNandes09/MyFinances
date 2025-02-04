package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.database.MyFinancesDatabase
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.ItemPurchasedDAO
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.time.LocalDate

class ItemPurchasedViewModel(
    private val itemRepository: ItemPurchasedDAO,
    private val paymentWayRepository: PaymentWayDAO,
    private val categoryRepository: CategoryDAO,
    private val personRepository: PersonDAO
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPaymentWay()
            loadCategory()
            loadPerson()
        }
    }

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

    private suspend fun loadPaymentWay() {
        _paymentWayList.value = paymentWayRepository.selectAllPaymentWay()
    }

    private suspend fun loadCategory() {
        _categoryList.value = categoryRepository.selectAllCategory()
    }

    private suspend fun loadPerson() {
        _personList.value = personRepository.selectAllPerson()
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

    fun saveItem(){

    }

    fun checkEverything(): Boolean{
        return true
    }
}