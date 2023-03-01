package tec.bd.weather.service;

import tec.bd.weather.model.Report;
import tec.bd.weather.storage.WeatherReportStorage;

import java.util.Date;

public class WeatherServiceImpl implements WeatherService {

    private final WeatherService remoteWeatherProvider;

    private final WeatherReportStorage weatherReportStorage;

    public WeatherServiceImpl(WeatherService remoteWeatherProvider,
                              WeatherReportStorage weatherReportStorage) {

        this.remoteWeatherProvider = remoteWeatherProvider;
        this.weatherReportStorage = weatherReportStorage;
    }

    @Override
    public Report getByZipCode(String zipCode) {

        // 1. Solicitar el dato a el almacenamiento local. Se envia el zipCode y
        // el almacenamiento deberá de resolver si hay datos
        var now = new Date(System.currentTimeMillis()).toString();
        var report = this.weatherReportStorage.find(zipCode);

        if (report != null) {
            return report;
        }

        // 2. Solicitar el reporte del clima de forma remota
        var weatheProviderReport = this.remoteWeatherProvider.getByZipCode(zipCode);

        // 3. Guardar el reporte obtenido de forma remota localmente
        weatherReportStorage.save(weatheProviderReport);

        return weatheProviderReport;
    }

    @Override
    public Report getByCity(String city) {
        return null;
    }

}
