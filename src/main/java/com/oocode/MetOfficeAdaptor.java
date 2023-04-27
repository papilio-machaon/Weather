package com.oocode;

import com.teamoptimization.LocatorClient;
import com.teamoptimization.MetOfficeForecasterClient;

import java.io.IOException;
import java.time.DayOfWeek;

public class MetOfficeAdaptor implements WeatherForecaster {

    @Override
    public MetOfficeForecasterClient.Forecast forecastFor(DayOfWeek dayOfWeek, String place) throws IOException {
        int dayNumber = dayOfWeek.getValue();
        LocatorClient.Location location = new LocatorClient().locationOf(place);
        MetOfficeForecasterClient forecasterClient = new MetOfficeForecasterClient();
        MetOfficeForecasterClient.Forecast forecast =
                forecasterClient.forecast(dayNumber, location.latitude, location.longitude);
        return forecast;
    }
}
