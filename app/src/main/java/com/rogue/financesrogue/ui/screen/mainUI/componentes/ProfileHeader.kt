package com.rogue.financesrogue.ui.screen.mainUI.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.R

//v1 - 09/01/25
@Composable
fun ProfileHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_photo_pic),
            contentDescription = null,
            Modifier.size(60.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Bem-vindo de volta,",
                fontSize = 18.sp
            )
            Text(
                text = "Gabriel",
                fontSize = 25.sp
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ProfileHeaderPrev() {
    ProfileHeader()
}