package com.example.platzitext.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.platzitext.R
import com.example.platzitext.state.CountryState

@Composable
fun CountryImage(
    url: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        error  = painterResource(id = R.drawable.ic_error),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(75.dp)
            .width(150.dp)
    )
}

@Composable
fun CountryItem(
    item: CountryState,
    onCountryClick: (String) -> Unit
) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(110.dp)
            .clickable  {
                onCountryClick(item.cioc)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .height(100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                CountryImage(url = item.flags.png)

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        text = item.name.common,
                        maxLines = 1,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp, bottom = 4.dp)
                    )

                    Text(
                        text = item.name.official,
                        maxLines = 2,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    )
                }
            }
        }
    }
}