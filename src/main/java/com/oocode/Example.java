package com.oocode;

import com.teamoptimization.MetOfficeForecasterClient;
import com.teamoptimization.NavyForecastingClient;

import java.io.IOException;
import java.time.DayOfWeek;

public class Example {
    public static void main(String[] args) throws IOException {
        WeatherForecaster weatherForecaster = new MetOfficeAdaptor();
        CachingForecaster cachingForecaster = new CachingForecaster(weatherForecaster);

        if (args.length != 2) {
            throw new RuntimeException("Must specify Day and Place");
        }
        forecast(args[0], args[1], cachingForecaster);
        forecast(args[0], args[1], cachingForecaster);
        forecast(args[0], args[1], cachingForecaster);
    }

    private static void forecast(String day, String place, WeatherForecaster weatherForecaster) throws IOException {
        MetOfficeForecasterClient.Forecast forecast = weatherForecaster.forecastFor(DayOfWeek.valueOf(day.toUpperCase()), place);
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);
    }

    private static void forecast2(String day, String place) throws IOException {
        NavyForecastingClient forecasting = new NavyForecastingClient();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());
        int minTemp = forecasting.min(dayOfWeek, place);
        int maxTemp = forecasting.max(dayOfWeek, place);
        String description = forecasting.desc(dayOfWeek, place);
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, minTemp, maxTemp, description);    }
}
