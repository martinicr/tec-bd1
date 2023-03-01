package tec.bd.openweather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SystemStubsExtension.class)
public class EnvironmentVariableAPIKeyProviderTest {

    @SystemStub
    private EnvironmentVariables environmentVariables;

    @Test
    public void GivenAPIKeyRequested_WhenAPIKeyAsEnvironmentVariable_ThenReturn() {
        environmentVariables.set("OW_API_KEY", "the-api-key");

        var apiKeyProvider = new EnvironmentVariableAPIKeyProvider();
        var actual = apiKeyProvider.getAPIKey();

        assertThat(actual).isEqualTo("the-api-key");
    }
}
