package tec.bd.weather.storage;

import tec.bd.weather.model.Report;

import java.util.List;

public interface WeatherReportStorage {

    void save(Report report);

    void remove(String reportKey);

    Report update(Report oldReport);

    Report find(String reportKey);

    List<Report> find();
}
