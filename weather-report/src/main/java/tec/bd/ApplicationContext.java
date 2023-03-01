package tec.bd;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tec.bd.openweather.EnvironmentVariableAPIKeyProvider;
import tec.bd.openweather.OpenWeatherAPIKeyProvider;
import tec.bd.openweather.OpenWeatherProvider;
import tec.bd.openweather.OpenWeatherResource;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;
import tec.bd.weather.storage.InMemoryWeatherReportStorage;
import tec.bd.weather.storage.WeatherReportStorage;

// TODO: Deberia de ser un Singleton
public class ApplicationContext {

    private final static String BASE_URL = "https://api.openweathermap.org";

    public OpenWeatherAPIKeyProvider openWeatherAPIKeyProvider;
    public OpenWeatherResource openWeatherResource;
    public WeatherService openWeatherService;
    public WeatherReportStorage weatherReportStorage;
    public WeatherService weatherService;

    public static ApplicationContext init() {
        ApplicationContext appContext = new ApplicationContext();
        appContext.openWeatherAPIKeyProvider = initOpenWeatherAPIKeyProvider();
        appContext.openWeatherResource = initOpenWeatherResource();
        appContext.openWeatherService = initOpenWeatherService(appContext.openWeatherResource,
                appContext.openWeatherAPIKeyProvider);
        appContext.weatherReportStorage = initWeatherReportStorage();
        appContext.weatherService = initWeatherService(appContext.openWeatherService, appContext.weatherReportStorage);

        return appContext;
    }

    public WeatherService getWeatherService() {
        return this.weatherService;
    }

    private static OpenWeatherAPIKeyProvider initOpenWeatherAPIKeyProvider() {
        return new EnvironmentVariableAPIKeyProvider();
    }

    private static OpenWeatherResource initOpenWeatherResource() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenWeatherResource.class);
    }

    private static WeatherService initOpenWeatherService(OpenWeatherResource openWeatherResource, OpenWeatherAPIKeyProvider openWeatherAPIKeyProvider) {
        return new OpenWeatherProvider(openWeatherResource, openWeatherAPIKeyProvider);
    }

    private static WeatherReportStorage initWeatherReportStorage() {
        return new InMemoryWeatherReportStorage();
    }

    private static WeatherService initWeatherService(WeatherService remoteWeatherService,
                                                      WeatherReportStorage weatherReportStorage) {
        return new WeatherServiceImpl(remoteWeatherService, weatherReportStorage);
    }
}
