package tec.bd.cli;

import picocli.CommandLine;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tec.bd.ApplicationContext;
import tec.bd.weather.WeatherReport;
import tec.bd.weather.service.OpenWeatherResource;
import tec.bd.weather.service.OpenWeatherService;
import tec.bd.weather.service.WeatherService;

@CommandLine.Command(name = "zip", description = "Get weather forecast by zip code")
public class ZipCodeCommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "zip code to be resolved")
    private String zipCode;

    @Override
    public void run() {
        var openWeatherService = APP_CONTEXT.openWeatherService;
        var weatherReport = new WeatherReport(openWeatherService);
        var report = weatherReport.getByZipCode(Integer.valueOf(zipCode));
        System.out.println(report.getTemperature());
    }

}
