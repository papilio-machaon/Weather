package com.oocode;

import com.teamoptimization.MetOfficeForecasterClient;
import com.teamoptimization.NavyForecastingClient;

import java.io.IOException;
import java.time.DayOfWeek;

public class Example {
    public static void main(String[] args) throws IOException {
        WeatherForecaster weatherForecasterMet = new MetOfficeAdaptor();
        WeatherForecaster weatherForecasterNavy = new NavyAdaptor();
        CachingForecaster cachingForecasterMet = new CachingForecaster(weatherForecasterMet);
        CachingForecaster cachingForecasterNavy = new CachingForecaster(weatherForecasterNavy);
        AveragingClient averagingClient = new AveragingClient(cachingForecasterMet, cachingForecasterNavy);

        if (args.length != 2) {
            throw new RuntimeException("Must specify Day and Place");
        }
        forecast(args[0], args[1], cachingForecasterMet);
        forecast(args[0], args[1], cachingForecasterMet);
        forecast(args[0], args[1], cachingForecasterMet);
        forecast(args[0], args[1], cachingForecasterNavy);
        forecast(args[0], args[1], cachingForecasterNavy);
        forecast(args[0], args[1], averagingClient);
        forecast(args[0], args[1], averagingClient);
        forecast(args[0], args[1], averagingClient);
    }

    private static void forecast(String day, String place, WeatherForecaster weatherForecaster) throws IOException {
        MetOfficeForecasterClient.Forecast forecast = weatherForecaster.forecastFor(DayOfWeek.valueOf(day.toUpperCase()), place);
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);
    }
}
