package com.teamoptimization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class MetOfficeForecasterClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();

    public static class Forecast {
        public int minTemp;
        public int maxTemp;
        public String description;

        public Forecast(int minTemp, int maxTemp, String description) {
            this.minTemp = minTemp;
            this.maxTemp = maxTemp;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Forecast forecast = (Forecast) o;
            return minTemp == forecast.minTemp && maxTemp == forecast.maxTemp && Objects.equals(description, forecast.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(minTemp, maxTemp, description);
        }

        public Forecast() {
        }

        @Override
        public String toString() {
            return "Forecast(" + minTemp + ", " + maxTemp + ", " + description + ")";
        }
    }

    public MetOfficeForecasterClient.Forecast forecast(int day, BigDecimal latitude, BigDecimal longitude) throws IOException {
        Request request = new Request.Builder()
                .url("https://k7kic7lc35.execute-api.eu-west-2.amazonaws.com/api/forecaster/" + day + "/" + latitude + "/" + longitude)
                .build();

        try (ResponseBody body = client.newCall(request).execute().body()) {
            return objectMapper.readValue(body.string(), MetOfficeForecasterClient.Forecast.class);
        }
    }
}
