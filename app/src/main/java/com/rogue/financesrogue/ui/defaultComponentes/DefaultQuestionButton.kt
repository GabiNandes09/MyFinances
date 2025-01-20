package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.R

@Composable
fun DefaultQuestionButton(modifier: Modifier = Modifier) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = R.drawable.help),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun DefafultQuestionButton() {
    DefaultQuestionButton()
}