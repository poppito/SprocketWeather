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
package extensions

import com.example.androiddevchallenge.model.Models
import com.example.androiddevchallenge.model.WeatherEvent

fun String.capitalCase(): String {
    val initial = toCharArray().first().toUpperCase()
    return initial.toString() + substring(1, this.length)
}

fun Models.CityItem.getForecastFromWeatherEvents(): String {
    when (primaryWeatherEvent) {
        is WeatherEvent.Sun -> {
            return when (primaryWeatherEvent.level) {
                0 -> {
                    "A cold day with temperature of 7 degrees, with a minium temperature of -3 degrees overnight"
                }
                1 -> {
                    "A pleasant day with a temperature of 25 degrees and a minium temperature of 14 degrees overnight"
                }
                2 -> {
                    "A warm day with a temperature of 36 degrees and a minium temperature of 21 degrees overnight"
                }
                else -> {
                    "A chilly day with a maximum temperature of -2 degrees and a minium temperature of -13 degrees overnight"
                }
            }
        }
        is WeatherEvent.Rain -> {
            return when (primaryWeatherEvent.level) {
                0 -> {
                    "A mostly clear day with patches of rain, less than 1 MM"
                }
                1 -> {
                    "A mild day but some rain forecast over the afternoon or late evening, about 3 MM"
                }
                2 -> {
                    "A cloudy day with a chance of thunderstorms and heavy rain throughout, about 12MM forecast - best to carry an umbrella!"
                }
                else -> {
                    "No chance of any rain today and perhaps a pleasant day for a picnic in the park!"
                }
            }
        }
        is WeatherEvent.Snow -> {
            return when (primaryWeatherEvent.level) {
                0 -> {
                    "Low chance of snow today - breaks in the overcast later - winds N at 15 to 20 mph"
                }
                1 -> {
                    "Medium chance of snow today - winds N at 12 to 15 mph. Forecast snow of about 2 inches"
                }
                2 -> {
                    "High chance of snow today - winds N at 30 to 40 mph. Forecast snow of about 6 inches"
                }
                else -> {
                    "no chance of snow today - winds N at 5 to 10 mph"
                }
            }
        }
    }
}
