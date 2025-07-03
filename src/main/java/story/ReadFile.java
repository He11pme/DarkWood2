package story;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public <T> T getData(String nameFile, TypeReference<T> typeReference) {
        Path path = Path.of("src/main/resources/" + nameFile + ".json");

        try (InputStream is = Files.newInputStream(path)) {
            return MAPPER.readValue(is, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
