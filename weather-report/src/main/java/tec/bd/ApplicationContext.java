package tec.bd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tec.bd.weather.WeatherReport;
import tec.bd.weather.service.OpenWeatherResource;
import tec.bd.weather.service.OpenWeatherService;
import tec.bd.weather.service.WeatherService;

public class ApplicationContext {

    private final static String BASE_URL = "https://api.openweathermap.org";

    public OpenWeatherResource openWeatherResource;
    public WeatherService openWeatherService;
    private WeatherReport weatherReport;

    public static ApplicationContext init() {
        ApplicationContext appContext = new ApplicationContext();
        appContext.openWeatherResource = initOpenWeatherResource();
        appContext.openWeatherService = initOpenWeatherService(appContext.openWeatherResource);

        return appContext;
    }

    private static OpenWeatherResource initOpenWeatherResource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenWeatherResource.class);
    }

    private static WeatherService initOpenWeatherService(OpenWeatherResource openWeatherResource)
    {
        return new OpenWeatherService(openWeatherResource);
    }
}
