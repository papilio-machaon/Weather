package com.develogical;

import com.oocode.CachingForecaster;
import com.oocode.WeatherForecaster;
import com.teamoptimization.MetOfficeForecasterClient;
import org.junit.Test;

import java.time.DayOfWeek;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CachingForecasterTest {
    @Test
    public void getsForecastFromDelegateIfNeverSeenBefore() {
        WeatherForecaster delegate = mock(WeatherForecaster.class);
        MetOfficeForecasterClient.Forecast expectedForecast = new MetOfficeForecasterClient.Forecast(1, 2, "cold");

        given(delegate.forecastFor(DayOfWeek.SATURDAY, "Oxford")).willReturn(expectedForecast);

        CachingForecaster cachingForecaster = new CachingForecaster(delegate);

        MetOfficeForecasterClient.Forecast actual = cachingForecaster.forecastFor(DayOfWeek.SATURDAY, "Oxford");

        assertThat(actual, equalTo(expectedForecast));
        verify(delegate, times(1)).forecastFor(DayOfWeek.SATURDAY, "Oxford");
    }

    @Test
    public void getsForecastFromCacheWhenCalledTwice() {
        WeatherForecaster delegate = mock(WeatherForecaster.class);
        MetOfficeForecasterClient.Forecast expectedForecast = new MetOfficeForecasterClient.Forecast(1, 2, "cold");

        given(delegate.forecastFor(DayOfWeek.SATURDAY, "Oxford")).willReturn(expectedForecast);

        CachingForecaster cachingForecaster = new CachingForecaster(delegate);

        cachingForecaster.forecastFor(DayOfWeek.SATURDAY, "Oxford");
        MetOfficeForecasterClient.Forecast actual = cachingForecaster.forecastFor(DayOfWeek.SATURDAY, "Oxford");

        assertThat(actual, equalTo(expectedForecast));
        verify(delegate, times(1)).forecastFor(DayOfWeek.SATURDAY, "Oxford");
    }
}
