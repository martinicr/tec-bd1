package tec.bd.openweather;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class OpenWeatherReport {

    private String base;
    private Clouds clouds;
    private int cod;
    private Coord coord;
    private long dt;
    private long id;
    private Main main;
    private String name;
    private Sys sys;
    private int timezone;
    private WeatherDetails[] weather;
    private int visibility;
    private Wind wind;

    public OpenWeatherReport() {

    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public WeatherDetails[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherDetails[] weather) {
        this.weather = weather;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "OpenWeatherReport{" +
                "base='" + base + '\'' +
                ", clouds=" + clouds +
                ", cod=" + cod +
                ", coord=" + coord +
                ", dt=" + dt +
                ", id=" + id +
                ", main=" + main +
                ", name='" + name + '\'' +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", weather=" + Arrays.toString(weather) +
                ", visibility=" + visibility +
                ", wind=" + wind +
                '}';
    }

    static class Clouds {

        private int all;

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    static class Coord {

        private float lat;
        private float lon;

        public Coord() {

        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }

    static class Main {

        private float feelsLike;
        private float humidity;
        private float pressure;
        private float temp;
        @SerializedName("temp_max")
        private float tempMax;
        @SerializedName("temp_min")
        private float tempMin;

        public Main() {

        }

        public float getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(float feelsLike) {
            this.feelsLike = feelsLike;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getTempMax() {
            return tempMax;
        }

        public void setTempMax(float tempMax) {
            this.tempMax = tempMax;
        }

        public float getTempMin() {
            return tempMin;
        }

        public void setTempMin(float tempMin) {
            this.tempMin = tempMin;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "feelsLike=" + feelsLike +
                    ", humidity=" + humidity +
                    ", pressure=" + pressure +
                    ", temp=" + temp +
                    ", tempMax=" + tempMax +
                    ", tempMin=" + tempMin +
                    '}';
        }
    }

    static class Sys {
        private String country;
        private int id;
        private long sunrise;
        private long sunset;
        private int type;

        public Sys() {

        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "country='" + country + '\'' +
                    ", id=" + id +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    ", type=" + type +
                    '}';
        }
    }

    public class WeatherDetails {
        private String description;
        private String icon;
        private int id;
        private String main;

        public WeatherDetails() {

        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        @Override
        public String toString() {
            return "WeatherDetails{" +
                    "description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", main='" + main + '\'' +
                    '}';
        }
    }

    public class Wind {
        private float deg;
        private float gust;
        private float speed;

        public Wind() {

        }

        public float getDeg() {
            return deg;
        }

        public void setDeg(float deg) {
            this.deg = deg;
        }

        public float getGust() {
            return gust;
        }

        public void setGust(float gust) {
            this.gust = gust;
        }

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "deg=" + deg +
                    ", gust=" + gust +
                    ", speed=" + speed +
                    '}';
        }
    }

/*

{
    "base": "stations",
    "clouds": {
        "all": 100
    },
    "cod": 200,
    "coord": {
        "lat": 10.0167,
        "lon": -84.2167
    },
    "dt": 1627956160,
    "id": 3624955,
    "main": {
        "feels_like": 294.08,
        "humidity": 85,
        "pressure": 1016,
        "temp": 293.75,
        "temp_max": 294,
        "temp_min": 293.75
    },
    "name": "Alajuela",
    "sys": {
        "country": "CR",
        "id": 2004086,
        "sunrise": 1627903597,
        "sunset": 1627948769,
        "type": 2
    },
    "timezone": -21600,
    "visibility": 1911,
    "weather": [
        {
            "description": "nubes",
            "icon": "04n",
            "id": 804,
            "main": "Clouds"
        }
    ],
    "wind": {
        "deg": 205,
        "gust": 1.33,
        "speed": 0.58
    }
}

* */

}
