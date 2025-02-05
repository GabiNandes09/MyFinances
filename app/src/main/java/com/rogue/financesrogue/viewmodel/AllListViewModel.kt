package com.rogue.financesrogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogue.financesrogue.database.dao.ItemPurchasedDAO
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllListViewModel(
    private val itemRepository: ItemPurchasedDAO
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadItens()
        }
    }

    private val _itemPurchasedList = MutableStateFlow<List<ItemPurchasedEntity>>(emptyList())
    val itemPurchasedList = _itemPurchasedList.asStateFlow()

    private suspend fun loadItens(){
        itemRepository.selectAllItemPurchased().collect{list ->
            _itemPurchasedList.value = list
        }
    }
}