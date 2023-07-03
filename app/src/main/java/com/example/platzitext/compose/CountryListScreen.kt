package com.example.platzitext.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.platzitext.R
import com.example.platzitext.viewmodel.CountryListViewModel

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel,
    onCountryClick: (String) -> Unit
) {
    val countryList = viewModel.list.collectAsState()
    val loading = viewModel.loading.collectAsState()

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
                    CountryItem(item = item, onCountryClick)
                }
            }
        }else{
            if(loading.value){
                Text(
                    text = "Loading list",
                    maxLines = 1,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(16.dp),
                )

            }else{
                Text(
                    text = "Currently network unavailable",
                    maxLines = 1,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(16.dp),
                )
            }
        }
    }

    if(loading.value){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_grey)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            val strokeWidth = 5.dp
            CircularProgressIndicator(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            Color.Blue,
                            radius = size.width / 2 - strokeWidth.toPx() / 2,
                            style = Stroke(strokeWidth.toPx())
                        )
                    },
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        }
    }
}