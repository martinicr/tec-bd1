package tec.bd.weather.storage;

import tec.bd.weather.model.Report;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryWeatherReportStorage implements WeatherReportStorage {

    private Map<String, Report> db;

    public InMemoryWeatherReportStorage() {
        this.db = new LinkedHashMap<>();
    }

    /*
     * Esto tiene que ser implementado
     *
     */

    @Override
    public void save(Report report) {
        db.put("un-key", report);
    }

    @Override
    public void remove(String reportKey) {

    }

    @Override
    public Report update(Report newReport) {
        /*
        *  var oldReport = this.db.get(generateKeyFromReport(newReport)
        *
        * if (oldReport != null)
        * {
        *   this.db.put(newReport);
        *   return newReport;
        * }
        *
        * return oldReport;
        * */

        return null;
    }

    @Override
    public Report find(String reportKey) {
        return null;
    }

    @Override
    public List<Report> find() {
        return null;
    }

    private String generateKeyFromReport(Report report) {
        var dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        return (dateFormat.format(report.getDate()) + "-" + report.getReportType());
    }
}
