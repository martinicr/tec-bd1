package tec.bd.weather;

import org.junit.jupiter.api.Test;
import tec.bd.weather.service.WeatherService;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

public class WeatherReportTest {

    @Test
    public void GivenZipCodeWithNegativeValue_WhenWeatherReportRequested_ThenException()
    {
        var openWeatherService = mock(WeatherService.class);
        var weatherReport = new WeatherReport(openWeatherService);
        try {
            weatherReport.getByZipCode(-1);
            fail("Negative values are not allowed");
        } catch (IllegalArgumentException e) {
            assertThat(true).isTrue();
        }
    }

    @Test
    public void GivenZipCodeWithOutOfRangeMaxValue_WhenWeatherReportRequested_ThenException()
    {
        var openWeatherService = mock(WeatherService.class);
        var weatherReport = new WeatherReport(openWeatherService);
        try {
            weatherReport.getByZipCode(1_000_000);
            fail("Zip code value is not allowed");
        } catch (IllegalArgumentException e) {
            assertThat(true).isTrue();
        }
        // c559e941a0da745aa0139aef272bf16c
    }

    @Test
    public void GivenZipCode_WhenWeatherReportRequested_ThenReturnReport() {

        var openWeatherService = mock(WeatherService.class);
        given(openWeatherService.getTemperature(anyInt())).willReturn(24f);

        var weatherReport = new WeatherReport(openWeatherService);

        var actual = weatherReport.getByZipCode(90210);

        verify(openWeatherService, times(1)).getTemperature(90210);

        assertThat(actual).isNotNull();
        assertThat(actual.getTemperature()).isEqualTo(24);
    }

}
