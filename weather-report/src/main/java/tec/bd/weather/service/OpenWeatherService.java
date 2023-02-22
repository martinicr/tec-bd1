package tec.bd.weather.service;

import retrofit2.Call;

import java.util.Map;

public class OpenWeatherService implements WeatherService {

    private final static String API_KEY = "test-api-key";

    private final OpenWeatherResource openWeatherResource;

    public OpenWeatherService(OpenWeatherResource openWeatherResource) {
        this.openWeatherResource = openWeatherResource;
    }

    @Override
    public float getTemperature(int zipCode) {
        try {
            var options = queryStringZipOptions(String.valueOf(zipCode));
            Call<OpenWeatherReport> openWeatherReportCall = this.openWeatherResource.get(options);
            OpenWeatherReport openWeatherReport = openWeatherReportCall.execute().body();
            return openWeatherReport.getMain().getTemp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error when calling open weather remote API");
        }
    }

    private Map<String, String> queryStringZipOptions(String zipCode) {
        return Map.of("zip", zipCode, "appId", API_KEY);
    }
}
