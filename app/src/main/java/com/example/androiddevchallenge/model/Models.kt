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
        val foreCast: String
    )
}

val cityItems = listOf<Models.CityItem>(
    Models.CityItem(
        name = "Sydney",
        country = "Australia",
        img = R.drawable.sydney,
        foreCast = ""
    ),
    Models.CityItem(
        name = "New York",
        country = "USA",
        img = R.drawable.ny,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Washington D.C.",
        country = "USA",
        img = R.drawable.dc,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Tokyo",
        country = "Japan",
        img = R.drawable.tokyo,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Barcelona",
        country = "Spain",
        img = R.drawable.barcelona,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Berlin",
        country = "Germany",
        img = R.drawable.berlin,
        foreCast = ""
    ),
    Models.CityItem(
        name = "London",
        country = "UK",
        img = R.drawable.london,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Hong Kong",
        country = "Hong Kong",
        img = R.drawable.hk,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Moscow",
        country = "Russia",
        img = R.drawable.moscow,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Athens",
        country = "Greece",
        img = R.drawable.athens,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Vienna",
        country = "Austria",
        img = R.drawable.vienna,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Paris",
        country = "France",
        img = R.drawable.paris,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Delhi",
        country = "India",
        img = R.drawable.delhi,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Rio De Janeiro",
        country = "Brazil",
        img = R.drawable.rdj,
        foreCast = ""
    ),
    Models.CityItem(
        name = "Dubai",
        country = "UAE",
        img = R.drawable.dubai,
        foreCast = ""
    )
)
