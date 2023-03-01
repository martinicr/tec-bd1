package tec.bd.openweather;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EnvironmentVariableAPIKeyProviderTest {

    @Ignore
    @Test
    public void GivenAPIKeyRequested_WhenAPIKeyAsEnvironmentVariable_ThenReturn() {

        var apiKeyProvider = new EnvironmentVariableAPIKeyProvider();
        var actual = apiKeyProvider.getAPIKey();

        assertThat(actual).isNotNull();
    }
}
