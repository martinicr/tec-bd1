package tec.bd.openweather;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

public class OpenWeatherProviderTest {

    @Test
    public void GivenZipCode_WhenOpenWeatherRemoteCall_ThenGetReport() throws Exception {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{" +
                "    \"base\": \"stations\"," +
                "    \"clouds\": {" +
                "        \"all\": 100" +
                "    }," +
                "    \"cod\": 200," +
                "    \"coord\": {" +
                "        \"lat\": 10.0167," +
                "        \"lon\": -84.2167" +
                "    }," +
                "    \"dt\": 1627956160," +
                "    \"id\": 3624955," +
                "    \"main\": {" +
                "        \"feels_like\": 294.08," +
                "        \"humidity\": 85," +
                "        \"pressure\": 1016," +
                "        \"temp\": 293.75," +
                "        \"temp_max\": 294," +
                "        \"temp_min\": 293.75" +
                "    },\n" +
                "    \"name\": \"Alajuela\"," +
                "    \"sys\": {\n" +
                "        \"country\": \"CR\"," +
                "        \"id\": 2004086," +
                "        \"sunrise\": 1627903597," +
                "        \"sunset\": 1627948769," +
                "        \"type\": 2" +
                "    },\n" +
                "    \"timezone\": -21600," +
                "    \"visibility\": 1911," +
                "    \"weather\": [" +
                "        {" +
                "            \"description\": \"nubes\"," +
                "            \"icon\": \"04n\"," +
                "            \"id\": 804," +
                "            \"main\": \"Clouds\"" +
                "        }" +
                "    ]," +
                "    \"wind\": {" +
                "        \"deg\": 205," +
                "        \"gust\": 1.33," +
                "        \"speed\": 0.58" +
                "    }" +
                "}"));

        server.start();

        HttpUrl baseUrl = server.url("/data/2.5/weather/");

        OpenWeatherResource openWeatherResource = createResource(baseUrl);
        OpenWeatherAPIKeyProvider openWeatherAPIKeyProvider = mock(OpenWeatherAPIKeyProvider.class);

        given(openWeatherAPIKeyProvider.getAPIKey()).willReturn("the-api-key");

        var openWeatherService = new OpenWeatherProvider(openWeatherResource, openWeatherAPIKeyProvider);

        var actual = openWeatherService.getByZipCode("90210");

        assertThat(actual).isNotNull();

        RecordedRequest request1 = server.takeRequest();

        assertThat(request1.getPath()).containsOnlyOnce("zip=90210");
        assertThat(request1.getPath()).containsOnlyOnce("appId=the-api-key");

        verify(openWeatherAPIKeyProvider, times(1)).getAPIKey();

        server.shutdown();
    }

    private static OpenWeatherResource createResource(HttpUrl baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenWeatherResource.class);
    }

}
