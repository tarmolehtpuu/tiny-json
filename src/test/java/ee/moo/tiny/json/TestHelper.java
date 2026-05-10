package ee.moo.tiny.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestHelper {

    public static String resource(String path, Object... args) {
        path = String.format(path, args);

        try (var is = JsonOrgTest.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException(String.format("Resource not found: %s", path));
            }

            try (var reader = new BufferedReader(new InputStreamReader(is, UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
