package com.example.platzitext.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.platzitext.viewmodel.CountryListViewModel

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel,
) {
    val countryList = viewModel.list.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(countryList.value.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(countryList.value) { item ->
                    CountryItem(item = item)
                }
            }
        }else{
            Text(
                text = "Currently network unavailable",
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}