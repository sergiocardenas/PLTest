package com.example.platzitext.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
    val page = viewModel.page.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    val fetch = viewModel.goPreviousPage()
                    if(fetch){
                        viewModel.searchCountryList(viewModel.page.value.region)
                    }
                },
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        colorResource(id = if(page.value.hasPrevious) R.color.purple_500 else R.color.grey),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "previous",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(
                        colorResource(id = R.color.purple_500),
                        shape = CircleShape
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = page.value.region,
                    maxLines = 1,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp),
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            IconButton(
                onClick = {
                    val fetch = viewModel.goNextPage()
                    if(fetch){
                        viewModel.searchCountryList(viewModel.page.value.region)
                    }
                },
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        colorResource(id = if(page.value.hasNext) R.color.purple_500 else R.color.grey),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "next",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
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