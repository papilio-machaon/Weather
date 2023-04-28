package com.oocode;

import com.teamoptimization.MetOfficeForecasterClient;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;

import static java.lang.Math.round;

public class AveragingClient implements WeatherForecaster {
    private WeatherForecaster forecaster1;
    private WeatherForecaster forecaster2;

    public AveragingClient(WeatherForecaster forecaster1, WeatherForecaster forecaster2) {

        this.forecaster1 = forecaster1;
        this.forecaster2 = forecaster2;
    }


    @Override
    public MetOfficeForecasterClient.Forecast forecastFor(DayOfWeek dayOfWeek, String place) throws IOException {
        int minTemp = round((forecaster1.forecastFor(dayOfWeek, place).minTemp + forecaster2.forecastFor(dayOfWeek, place).minTemp) / 2);
        int maxTemp = round((forecaster1.forecastFor(dayOfWeek, place).maxTemp + forecaster2.forecastFor(dayOfWeek, place).maxTemp) / 2);
        String description = forecaster1.forecastFor(dayOfWeek, place).description;
        MetOfficeForecasterClient.Forecast forecast = new MetOfficeForecasterClient.Forecast(minTemp, maxTemp, description);
        return forecast;
    }
}
