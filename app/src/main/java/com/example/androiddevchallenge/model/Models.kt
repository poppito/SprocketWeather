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
package com.example.androiddevchallenge.model

import com.example.androiddevchallenge.Constants.Companion.RAINY
import com.example.androiddevchallenge.Constants.Companion.SNOWY
import com.example.androiddevchallenge.Constants.Companion.SUNNY
import com.example.androiddevchallenge.R

class Models {
    data class CityItem(
        val name: String,
        val country: String,
        val img: Int,
        val foreCast: Int,
        val primaryWeatherEvent: WeatherEvent,
        val map: Int,
        val weatherPreviews: List<WeatherPreviewItem>
    )

    data class WeatherPreviewItem(
        val temperature: Int,
        val day: Int,
        val weather: String
    )
}

sealed class WeatherEvent {
    data class Sun(val level: Int) : WeatherEvent()
    data class Snow(val level: Int) : WeatherEvent()
    data class Rain(val level: Int) : WeatherEvent()
}

val cityItems = listOf<Models.CityItem>(
    Models.CityItem(
        name = "Sydney",
        country = "Australia",
        img = R.drawable.sydney,
        foreCast = 30,
        primaryWeatherEvent = WeatherEvent.Sun(level = 2),
        map = R.drawable.sydney_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(32, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(27, day = R.string.txt_wednesday, weather = RAINY),
            Models.WeatherPreviewItem(32, day = R.string.txt_thursday, weather = SUNNY),
            Models.WeatherPreviewItem(32, day = R.string.txt_friday, weather = RAINY),
            Models.WeatherPreviewItem(32, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "New York",
        country = "USA",
        img = R.drawable.ny,
        foreCast = -3,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        map = R.drawable.ny_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(-1, day = R.string.txt_tomorrow, weather = SNOWY),
            Models.WeatherPreviewItem(1, day = R.string.txt_wednesday, weather = SNOWY),
            Models.WeatherPreviewItem(11, day = R.string.txt_thursday, weather = SUNNY),
            Models.WeatherPreviewItem(16, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(14, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Tokyo",
        country = "Japan",
        img = R.drawable.tokyo,
        foreCast = 24,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        map = R.drawable.tokyo_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(27, day = R.string.txt_tomorrow, weather = RAINY),
            Models.WeatherPreviewItem(26, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(30, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(25, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(23, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Barcelona",
        country = "Spain",
        img = R.drawable.barcelona,
        foreCast = 25,
        primaryWeatherEvent = WeatherEvent.Sun(level = 2),
        map = R.drawable.barcelona_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(30, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(32, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(30, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(24, day = R.string.txt_friday, weather = RAINY),
            Models.WeatherPreviewItem(27, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Berlin",
        country = "Germany",
        img = R.drawable.berlin,
        foreCast = 3,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        map = R.drawable.berlin_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(1, day = R.string.txt_tomorrow, weather = SNOWY),
            Models.WeatherPreviewItem(2, day = R.string.txt_wednesday, weather = SNOWY),
            Models.WeatherPreviewItem(10, day = R.string.txt_thursday, weather = SUNNY),
            Models.WeatherPreviewItem(7, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(11, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "London",
        country = "UK",
        img = R.drawable.london,
        foreCast = 14,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        map = R.drawable.london_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(17, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(17, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(20, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(19, day = R.string.txt_friday, weather = RAINY),
            Models.WeatherPreviewItem(17, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Hong Kong",
        country = "Hong Kong",
        img = R.drawable.hk,
        foreCast = 33,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        map = R.drawable.hk_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(28, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(32, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(33, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(33, day = R.string.txt_friday, weather = RAINY),
            Models.WeatherPreviewItem(29, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Athens",
        country = "Greece",
        img = R.drawable.athens,
        foreCast = 25,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        map = R.drawable.athens_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(19, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(19, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(23, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(25, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(27, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Vienna",
        country = "Austria",
        img = R.drawable.vienna,
        foreCast = 4,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        map = R.drawable.vienna_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(3, day = R.string.txt_tomorrow, weather = SNOWY),
            Models.WeatherPreviewItem(7, day = R.string.txt_wednesday, weather = SNOWY),
            Models.WeatherPreviewItem(11, day = R.string.txt_thursday, weather = SUNNY),
            Models.WeatherPreviewItem(16, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(14, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Paris",
        country = "France",
        img = R.drawable.paris,
        foreCast = 24,
        primaryWeatherEvent = WeatherEvent.Rain(level = 1),
        map = R.drawable.paris_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(19, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(19, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(23, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(25, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(27, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Delhi",
        country = "India",
        img = R.drawable.delhi,
        foreCast = 35,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        map = R.drawable.delhi_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(27, day = R.string.txt_tomorrow, weather = RAINY),
            Models.WeatherPreviewItem(26, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(30, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(25, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(23, day = R.string.txt_saturday, weather = SUNNY),
        )
    ),
    Models.CityItem(
        name = "Rio De Janeiro",
        country = "Brazil",
        img = R.drawable.rdj,
        foreCast = 36,
        primaryWeatherEvent = WeatherEvent.Sun(level = 1),
        map = R.drawable.rdj_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(33, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(34, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(30, day = R.string.txt_thursday, weather = RAINY),
            Models.WeatherPreviewItem(22, day = R.string.txt_friday, weather = RAINY),
            Models.WeatherPreviewItem(21, day = R.string.txt_saturday, weather = RAINY),
        )
    ),
    Models.CityItem(
        name = "Abu Dhabi",
        country = "UAE",
        img = R.drawable.dubai,
        foreCast = 38,
        primaryWeatherEvent = WeatherEvent.Sun(level = 3),
        map = R.drawable.abu_dhabi_map,
        weatherPreviews = listOf(
            Models.WeatherPreviewItem(40, day = R.string.txt_tomorrow, weather = SUNNY),
            Models.WeatherPreviewItem(39, day = R.string.txt_wednesday, weather = SUNNY),
            Models.WeatherPreviewItem(39, day = R.string.txt_thursday, weather = SUNNY),
            Models.WeatherPreviewItem(40, day = R.string.txt_friday, weather = SUNNY),
            Models.WeatherPreviewItem(41, day = R.string.txt_saturday, weather = SUNNY),
        )
    )
)
