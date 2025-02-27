package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.database.dao.FixedValueDAO
import com.rogue.financesrogue.database.dao.ItemPurchasedDAO
import com.rogue.financesrogue.database.dao.ParcelValuesDAO
import com.rogue.financesrogue.database.dao.ValueToReceiveDAO
import com.rogue.financesrogue.database.entities.FixedValueEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.ParcelValueEntity
import com.rogue.financesrogue.database.entities.ValueToReceiveEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllListViewModel(
    private val itemRepository: ItemPurchasedDAO,
    private val valuesToReceiveRepository: ValueToReceiveDAO,
    private val fixedValueRepository: FixedValueDAO,
    private val parcelValuesRepository: ParcelValuesDAO
) : ViewModel() {

    private val _itemPurchasedList = MutableStateFlow<List<ItemPurchasedEntity>>(emptyList())
    val itemPurchasedList = _itemPurchasedList.asStateFlow()

    private val _valueToReceiveList = MutableStateFlow<List<ValueToReceiveEntity>>(emptyList())
    val valueToReceiveList = _valueToReceiveList.asStateFlow()

    private val _fixedValueList = MutableStateFlow<List<FixedValueEntity>>(emptyList())
    val fixedValueList = _fixedValueList.asStateFlow()

    private val _parcelValuesList = MutableStateFlow<List<ParcelValueEntity>>(emptyList())
    val parcelValuesList = _parcelValuesList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { loadItens() }
            launch { loadValuesToReceive() }
            launch { loadFixedValues() }
            launch { loadParcelValues() }
        }
    }

    private suspend fun loadItens(){
        itemRepository.selectAllItemPurchased().collect{list ->
            _itemPurchasedList.value = list
        }
    }

    private suspend fun loadValuesToReceive(){
        valuesToReceiveRepository.selectAllValueToRecceive().collect{list ->
            _valueToReceiveList.value = list
        }
    }

    private suspend fun loadFixedValues(){
        fixedValueRepository.selectAllFixedValue().collect{list ->
            _fixedValueList.value = list
        }
    }

    private suspend fun loadParcelValues(){
        parcelValuesRepository.selectAllParcelValue().collect{list ->
            _parcelValuesList.value = list
        }
    }
}