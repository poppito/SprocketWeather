/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.Constants.Companion.ROTATION_ANGLE
import com.example.androiddevchallenge.model.Models
import com.example.androiddevchallenge.model.WeatherEvent
import com.example.androiddevchallenge.model.cityItems
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.bodyFont
import com.example.androiddevchallenge.ui.theme.headerFont
import com.example.androiddevchallenge.ui.theme.italicFont
import extensions.capitalCase
import extensions.getForecastFromWeatherEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CitiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                AddLayoutElements(data = cityItems)
            }
        }
    }
}

@Composable
fun AddLayoutElements(data: List<Models.CityItem>) {
    Surface(color = MaterialTheme.colors.background) {
        val listState = rememberLazyListState()
        val selection = remember { mutableStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Cities(data = data, state = listState, selection = selection)
            Spacer(modifier = Modifier.fillMaxSize(0.2f))
            Sprocket(
                listState = listState,
                coroutineScope = coroutineScope,
                selection = selection
            )
        }
    }
}

@Composable
private fun Sprocket(
    listState: LazyListState,
    coroutineScope: CoroutineScope,
    selection: MutableState<Int>
) {
    val context = LocalContext.current
    val rotation = remember { mutableStateOf(0f) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.btn_left),
            contentDescription = stringResource(
                id = R.string.cd_btn_left
            ),
            modifier = Modifier
                .clickable {
                    if (selection.value > 0) {
                        selection.value = selection.value - 1
                        rotation.value = rotation.value - ROTATION_ANGLE
                    }
                    coroutineScope.launch {
                        listState.scrollToItem(
                            index = selection.value
                        )
                    }
                }
                .padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.sprocket),
            contentDescription = stringResource(id = R.string.cd_sprocket),
            modifier = Modifier
                .rotate(rotation.value)
                .padding(16.dp)
                .clickable(
                    onClick = {
                        // TODO
                    }
                )
        )
        Image(
            painter = painterResource(id = R.drawable.btn_right),
            contentDescription = stringResource(
                id = R.string.cd_btn_right
            ),
            modifier = Modifier
                .clickable {
                    if (selection.value < cityItems.size - 1) {
                        selection.value = selection.value + 1
                        rotation.value = rotation.value + ROTATION_ANGLE
                    }
                    coroutineScope.launch {
                        listState.animateScrollToItem(
                            index = selection.value
                        )
                    }
                }
                .padding(8.dp)
        )
    }
}

@Composable
private fun Cities(
    data: List<Models.CityItem>,
    state: LazyListState,
    selection: MutableState<Int>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        state = state
    ) {
        // items
        items(data.size) { index ->
            City(data = data[index], selected = selection.value == index)
        }
    }
}

@Composable
private fun City(data: Models.CityItem, selected: Boolean = false) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.primaryVariant)
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = data.img),
                        contentDescription = stringResource(id = R.string.cd_city_img, data.name),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(100.dp)
                            .zIndex(1f)
                    )
                    Text(
                        text = data.name.capitalCase(),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .zIndex(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = data.country.capitalCase(),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .zIndex(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2
                    )
                }
            }
        } else {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = data.img),
                    contentDescription = stringResource(id = R.string.cd_city_img, data.name),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(150.dp)
                        .zIndex(1f)
                )
                Text(
                    text = data.name.capitalCase(),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .zIndex(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = data.country.capitalCase(),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .zIndex(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }
}

@Composable
private fun ShowWeatherAnimation(
    primaryWeatherEvent: WeatherEvent
) {
    when (primaryWeatherEvent) {
        is WeatherEvent.Sun -> {
            if (primaryWeatherEvent.level > 1) {
                ShowSun()
            } else {
                ShowOvercast()
            }
        }
        is WeatherEvent.Rain -> {
            if (primaryWeatherEvent.level > 1) {
                ShowRain()
            } else {
                // RainAndSun()
            }
        }
        is WeatherEvent.Snow -> {
            if (primaryWeatherEvent.level > 1) {
                ShowSnow()
            } else {
                // SunAndSnow()
            }
        }
    }
}

@Composable
fun CityWeatherDetail(data: Models.CityItem) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = if (data.primaryWeatherEvent is WeatherEvent.Snow) Alignment.CenterHorizontally else Alignment.End
            ) {
                ShowWeatherAnimation(primaryWeatherEvent = data.primaryWeatherEvent)
                Text(
                    text = stringResource(
                        id = R.string.txt_degrees, data.foreCast
                    ),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 40.sp,
                        fontFamily = headerFont
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 48.dp, end = 16.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = data.name,
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 24.sp,
                        fontFamily = headerFont
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = data.country,
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 22.sp,
                        fontFamily = bodyFont
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = data.getForecastFromWeatherEvents(),
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 20.sp,
                        fontFamily = italicFont
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ShowOvercast() {
    Box {
        ShowSun()
        Cloud()
    }
}

@Composable
private fun ShowSun() {
    val offsetTransition = rememberInfiniteTransition()
    val offset = offsetTransition.animateFloat(
        initialValue = 0f,
        targetValue = 180f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Image(
        painter = painterResource(id = R.drawable.sun),
        contentDescription = "",
        modifier = Modifier
            .padding(16.dp)
            .size(width = 140.dp, height = 140.dp)
            .offset(
                x = -offset.value.dp, y = 0.dp
            )
    )
}

@Composable
private fun ShowSnow() {
    val infiniteTransition = rememberInfiniteTransition()
    val tx = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val rotate = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.snowflake1),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = 1.dp, y = (tx.value + 1).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake2),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = 0.dp, y = (tx.value + 3).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake3),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = -3.dp, y = tx.value.dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake4),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = 2.dp, y = (tx.value + 5).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake1),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = -2.dp, y = (tx.value + 1).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake2),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = 0.dp, y = (tx.value + 3).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake3),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = -1.dp, y = tx.value.dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake4),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = 1.dp, y = (tx.value + 5).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
        Image(
            painter = painterResource(id = R.drawable.snowflake1),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .offset(x = 1.dp, y = (tx.value + 1).dp)
                .rotate(rotate.value),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
        )
    }
}

@Composable
private fun ShowRain() {
    Cloud()
    Rain()
}

@Composable
private fun Cloud() {
    val infiniteTransition = rememberInfiniteTransition()
    val tx = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 240f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = "",
        modifier = Modifier
            .padding(top = 24.dp, end = 16.dp)
            .offset(x = -tx.value.dp, y = 0.dp),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
    )
}

@Composable
private fun Rain() {
    val infiniteTransition = rememberInfiniteTransition()
    val tx = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Image(
        painter = painterResource(id = R.drawable.rain),
        contentDescription = "",
        modifier = Modifier
            .offset(x = -tx.value.dp, y = tx.value.dp)
            .padding(end = 48.dp)
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        AddLayoutElements(data = cityItems)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        AddLayoutElements(data = cityItems)
    }
}
