package news.agoda.com.sample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import news.agoda.com.sample.model.domain.Domains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class APIAvailabilityTest {

    @Test
    public void testAvailability() throws Exception {
        URLConnection connection = new URL(
            "http://www.mocky.io/v2/573c89f31100004a1daa8adb").openConnection();
        InputStream response = connection.getInputStream();

        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(response, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }

        assert buffer.length() > 0;
    }

    @Test
    public void testApiAvailability() throws Exception {
        URLConnection connection = new URL(Domains.NEWS_URL + "bins/nl6jh").openConnection();
        InputStream response = connection.getInputStream();

        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(response, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }
        assert buffer.length() > 0;
    }
}
