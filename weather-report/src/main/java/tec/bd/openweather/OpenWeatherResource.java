package tec.bd.openweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface OpenWeatherResource {

    @GET ("/data/2.5/weather")
    Call<OpenWeatherReport> get(@QueryMap Map<String, String> options);
}
