package com.rogue.financesrogue.ui.screen.allListUI.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.FixedValueEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.ParcelValueEntity
import com.rogue.financesrogue.database.entities.ValueToReceiveEntity
import org.koin.compose.koinInject

@Composable
fun ItemAllList(
    item: Any
) {
    val categoryRepository: CategoryDAO = koinInject()

    val itemList = remember { mutableStateOf<ItemAllList?>(null) }

    LaunchedEffect(item) {
        itemList.value = convertIntoItem(item, categoryRepository)
    }

    Card(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.5.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            //Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
            Text(text = itemList.value?.category?.categoryId.toString())
            Text(text = itemList.value?.description ?: "Error")
            Text(text = "R$ ${itemList.value?.value}")
        }
    }
}

private suspend fun convertIntoItem(item: Any, categoryRepository: CategoryDAO): ItemAllList? {
    when (item) {
        is ItemPurchasedEntity -> {
            return ItemAllList(
                description = item.description,
                value = item.price,
                category = categoryRepository.selectOneCategory(item.idCategory)
            )
        }
        is ValueToReceiveEntity -> {
            return ItemAllList(
                description = item.description,
                value = item.totalPrice,
                category = categoryRepository.selectOneCategory(1) //todo pensar em o que colocar aqui
            )
        }
        is FixedValueEntity -> {
            return ItemAllList(
                description = item.description,
                value = item.price,
                category = categoryRepository.selectOneCategory(item.idCategory)
            )
        }
        is ParcelValueEntity -> {
            return ItemAllList(
                description = item.description,
                value = item.parcelPrice,
                category = categoryRepository.selectOneCategory(item.idCategory)
            )
        }
    }
    return null
}

private data class ItemAllList(
    val description: String,
    val value: Double,
    val category: CategoryEntity
)

