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
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.platzitext.R
import com.example.platzitext.viewmodel.CountryDetailViewModel

@Composable
fun CountryDetailScreen(
    viewModel: CountryDetailViewModel,
) {
    val item = viewModel.item.collectAsState()
    val loading = viewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        if(item.value.name.common.isEmpty() &&
            item.value.flags.png.isEmpty() &&
            item.value.cioc.isEmpty()
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Loading info",
                        maxLines = 1,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(16.dp)
                    )

                }
            }
        }else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.value.flags.png)
                        .crossfade(true)
                        .build(),
                    error  = painterResource(id = R.drawable.ic_error),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(160.dp)
                        .width(320.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Name: "+item.value.name.common,
                        maxLines = 1,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Official Name: "+item.value.name.official,
                        maxLines = 1,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Code: "+item.value.cioc,
                        maxLines = 1,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if(item.value.independent)
                            "Independent"
                        else
                            "Not Independent",
                        maxLines = 1,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    if(item.value.unMember){
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "UN Member",
                            maxLines = 1,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }else{
                        Spacer(modifier = Modifier.height(36.dp))
                    }
                }
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