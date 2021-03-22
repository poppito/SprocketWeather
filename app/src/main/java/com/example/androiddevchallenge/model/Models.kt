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

import com.example.androiddevchallenge.R

class Models {
    data class CityItem(
        val name: String,
        val country: String,
        val img: Int,
        val foreCast: Int,
        val primaryWeatherEvent: WeatherEvent,
        val secondaryWeatherEvent: WeatherEvent
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
        secondaryWeatherEvent = WeatherEvent.Rain(level = 1)
    ),
    Models.CityItem(
        name = "New York",
        country = "USA",
        img = R.drawable.ny,
        foreCast = -3,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = -1)
    ),
    Models.CityItem(
        name = "Washington D.C.",
        country = "USA",
        img = R.drawable.dc,
        foreCast = -4,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = -1)
    ),
    Models.CityItem(
        name = "Tokyo",
        country = "Japan",
        img = R.drawable.tokyo,
        foreCast = 24,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = 1)
    ),
    Models.CityItem(
        name = "Barcelona",
        country = "Spain",
        img = R.drawable.barcelona,
        foreCast = 25,
        primaryWeatherEvent = WeatherEvent.Sun(level = 2),
        secondaryWeatherEvent = WeatherEvent.Rain(level = 1)
    ),
    Models.CityItem(
        name = "Berlin",
        country = "Germany",
        img = R.drawable.berlin,
        foreCast = 3,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = -1)
    ),
    Models.CityItem(
        name = "London",
        country = "UK",
        img = R.drawable.london,
        foreCast = 14,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = 0)
    ),
    Models.CityItem(
        name = "Hong Kong",
        country = "Hong Kong",
        img = R.drawable.hk,
        foreCast = 33,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = 2)
    ),
    Models.CityItem(
        name = "Moscow",
        country = "Russia",
        img = R.drawable.moscow,
        foreCast = 1,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = 0)
    ),
    Models.CityItem(
        name = "Athens",
        country = "Greece",
        img = R.drawable.athens,
        foreCast = 25,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = 2)
    ),
    Models.CityItem(
        name = "Vienna",
        country = "Austria",
        img = R.drawable.vienna,
        foreCast = 4,
        primaryWeatherEvent = WeatherEvent.Snow(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = -1)
    ),
    Models.CityItem(
        name = "Paris",
        country = "France",
        img = R.drawable.paris,
        foreCast = 24,
        secondaryWeatherEvent = WeatherEvent.Rain(level = 1),
        primaryWeatherEvent = WeatherEvent.Sun(level = 1),
    ),
    Models.CityItem(
        name = "Delhi",
        country = "India",
        img = R.drawable.delhi,
        foreCast = 35,
        primaryWeatherEvent = WeatherEvent.Rain(level = 2),
        secondaryWeatherEvent = WeatherEvent.Sun(level = 2)
    ),
    Models.CityItem(
        name = "Rio De Janeiro",
        country = "Brazil",
        img = R.drawable.rdj,
        foreCast = 36,
        primaryWeatherEvent = WeatherEvent.Sun(level = 1),
        secondaryWeatherEvent = WeatherEvent.Rain(level = 1)
    ),
    Models.CityItem(
        name = "Dubai",
        country = "UAE",
        img = R.drawable.dubai,
        foreCast = 38,
        primaryWeatherEvent = WeatherEvent.Sun(level = 3),
        secondaryWeatherEvent = WeatherEvent.Rain(level = -1)
    )
)
