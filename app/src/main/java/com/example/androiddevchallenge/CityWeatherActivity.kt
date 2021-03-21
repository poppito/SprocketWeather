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
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.Constants.Companion.CITY_NAME
import com.example.androiddevchallenge.model.Models
import com.example.androiddevchallenge.model.WeatherEvent
import com.example.androiddevchallenge.model.cityItems
import com.example.androiddevchallenge.ui.theme.MyTheme

class CityWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val name = intent.getStringExtra(CITY_NAME) ?: return@MyTheme
                val data = cityItems.firstOrNull { it.name == name } ?: return@MyTheme
                Surface(color = MaterialTheme.colors.background) {
                    CityWeatherDetail(data = data)
                }
            }
        }
    }
}

enum class RainState() {
    START,
    END
}

@Composable
private fun ShowAnimation(
    primaryWeatherEvent: WeatherEvent
) {
    when (primaryWeatherEvent) {
        is WeatherEvent.Sun -> {
            if (primaryWeatherEvent.level > 1) {
                // Sun()
            } else {
                // Overcast()
            }
        }
        is WeatherEvent.Rain -> {
            if (primaryWeatherEvent.level > 1) {
                // Rain()
            } else {
                // RainAndSun()
            }
        }
        is WeatherEvent.Snow -> {
            if (primaryWeatherEvent.level > 1) {
                // Snow()
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
                .fillMaxHeight(0.5f)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize(0.7f)) {
                    Text(
                        text = stringResource(
                            id = R.string.txt_degrees, data.foreCast
                        ),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.padding(start = 16.dp, top = 48.dp)
                    )
                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                    Text(
                        text = data.country,
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(width = 120.dp, height = 120.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.snowflake),
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp)
                    )
                    Rain()
                    Cloud()
                }
            }
        }
    }
}

@Composable
fun Cloud() {
    val infiniteTransition = rememberInfiniteTransition()
    val tx = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = "",
        modifier = Modifier
            .padding(top = 24.dp, end = 16.dp)
            .offset(x = -tx.value.dp, y = 0.dp)
    )
}

@Composable
fun Rain() {
    val infiniteTransition = rememberInfiniteTransition()
    val tx = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Image(
        painter = painterResource(id = R.drawable.rain),
        contentDescription = "",
        modifier = Modifier
            .offset(x = -tx.value.dp, y = tx.value.dp)
            .padding(top = 68.dp, end = 24.dp)
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun BrightPreview() {
    MyTheme {
        CityWeatherDetail(data = cityItems[0])
    }
}
