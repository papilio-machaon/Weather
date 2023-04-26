package com.oocode;

import com.oocode.WeatherForecaster;
import com.teamoptimization.MetOfficeForecasterClient;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CachingForecaster implements WeatherForecaster {
    private final WeatherForecaster delegate;
    private List<StoredForecast> storedForecasts;

    public CachingForecaster(WeatherForecaster delegate) {

        this.delegate = delegate;
        this.storedForecasts = new ArrayList<>();
    }

    @Override
    public MetOfficeForecasterClient.Forecast forecastFor(DayOfWeek dayOfWeek, String place) {
        for (int i=0; i < storedForecasts.size(); i++) {
            if( dayOfWeek.equals(storedForecasts.get(i).dayOfWeek) && place.equals(storedForecasts.get(i).place)) {
                return storedForecasts.get(i).forecast;
            }
        }
        MetOfficeForecasterClient.Forecast forecast = new MetOfficeForecasterClient.Forecast();
        forecast = delegate.forecastFor(dayOfWeek, place);
        StoredForecast storedForecast = new StoredForecast(dayOfWeek, place, forecast);
        storedForecasts.add(storedForecast);
        return forecast;
    }
}
