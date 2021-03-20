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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.Constants.Companion.ROTATION_ANGLE
import com.example.androiddevchallenge.model.Models
import com.example.androiddevchallenge.model.cityItems
import com.example.androiddevchallenge.ui.theme.MyTheme
import extensions.capitalCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(data = cityItems)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(data: List<Models.CityItem>) {
    Surface(color = MaterialTheme.colors.background) {
        val offsetX = remember { mutableStateOf(0f) }
        val listState = rememberLazyListState()
        val selection = remember { mutableStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
        val animating = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Cities(data = data, state = listState, selection = selection)
            Spacer(modifier = Modifier.fillMaxSize(0.2f))
            Sprocket(
                offsetX = offsetX,
                listState = listState,
                coroutineScope = coroutineScope,
                selection = selection
            )
        }
    }
}

@Composable
private fun Sprocket(
    offsetX: MutableState<Float>,
    listState: LazyListState,
    coroutineScope: CoroutineScope,
    selection: MutableState<Int>
) {
    val rotation = remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .rotate(rotation.value)
                .clickable(
                    onClick = {
                        // TODO
                    }
                ),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.sprocket),
                contentDescription = stringResource(id = R.string.cd_sprocket)
            )
        }

        Row {
            Image(
                painter = painterResource(id = R.drawable.btn_left),
                contentDescription = stringResource(
                    id = R.string.cd_btn_left
                ),
                modifier = Modifier
                    .clickable {
                        rotation.value = rotation.value - ROTATION_ANGLE
                        if (selection.value >= 0) {
                            selection.value = selection.value - 1
                        }
                        coroutineScope.launch {
                            listState.animateScrollToItem(
                                index = selection.value
                            )
                        }
                    }
                    .padding(8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.btn_right),
                contentDescription = stringResource(
                    id = R.string.cd_btn_right
                ),
                modifier = Modifier
                    .clickable {
                        rotation.value = rotation.value + ROTATION_ANGLE
                        if (selection.value < cityItems.size - 1) {
                            selection.value = selection.value + 1
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

        Text(
            text = stringResource(id = R.string.txt_sprocket_instructions),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = data.img),
            contentDescription = stringResource(id = R.string.cd_city_img, data.name),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
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

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(data = cityItems)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(data = cityItems)
    }
}
