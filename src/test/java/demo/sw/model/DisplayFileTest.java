package demo.sw.model;

import demo.sw.document.Document;
import demo.sw.document.displayfile.DisplayFile;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DisplayFileTest {

    @Nested
    class DeserializeTests {

        @Test
        void returnDeserializedDisplayFileWhenGivenMinimumOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "DisplayFile",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
                          "path": "./some/path/File.java"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize display file entry with minimum options")
                    .isEqualTo(Document.of(DisplayFile.of(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"), "./some/path/File.java")));
        }

        @Test
        void returnDeserializedDisplayFileWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "DisplayFile",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
                          "from_line": 5,
                          "number_of_lines": 3,
                          "path": "./some/path/File.java"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize display file entry when given all options")
                    .isEqualTo(Document.of(DisplayFile.of(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"), "./some/path/File.java", 5, 3)));
        }
    }
}