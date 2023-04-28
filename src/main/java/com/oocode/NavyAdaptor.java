package com.oocode;

import com.teamoptimization.MetOfficeForecasterClient;
import com.teamoptimization.NavyForecastingClient;

import java.io.IOException;
import java.time.DayOfWeek;

public class NavyAdaptor implements WeatherForecaster {

    @Override
    public MetOfficeForecasterClient.Forecast forecastFor(DayOfWeek dayOfWeek, String place) throws IOException {
        NavyForecastingClient forecasting = new NavyForecastingClient();
        int minTemp = forecasting.min(dayOfWeek, place);
        int maxTemp = forecasting.max(dayOfWeek, place);
        String description = forecasting.desc(dayOfWeek, place);
        MetOfficeForecasterClient.Forecast forecast = new MetOfficeForecasterClient.Forecast(minTemp, maxTemp, description);
        return forecast;
    }
}
