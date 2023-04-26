package com.oocode;

import com.teamoptimization.MetOfficeForecasterClient;

import java.time.DayOfWeek;

public interface WeatherForecaster {
    MetOfficeForecasterClient.Forecast forecastFor(DayOfWeek dayOfWeek, String place);
}
