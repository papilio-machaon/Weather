package com.oocode;

import com.teamoptimization.MetOfficeForecasterClient;

import java.time.DayOfWeek;

public class StoredForecast {
        public DayOfWeek dayOfWeek;

        public String place;

        public MetOfficeForecasterClient.Forecast forecast;

        public StoredForecast(DayOfWeek dayOfWeek, String place, MetOfficeForecasterClient.Forecast forecast) {
            this.dayOfWeek = dayOfWeek;
            this.place = place;
            this.forecast = forecast;
        }
}
