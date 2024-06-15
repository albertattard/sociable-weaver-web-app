package demo.sw.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.OptionalInt;

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
                          "path": "./some/path/File.java"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize display file entry with minimum options")
                    .isEqualTo(Document.of(DisplayFile.of(Path.of("./some/path/File.java"))));
        }

        @Test
        void returnDeserializedDisplayFileWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "DisplayFile",
                          "from_line": 5,
                          "number_of_lines": 3,
                          "path": "./some/path/File.java"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize display file entry when given all options")
                    .isEqualTo(Document.of(DisplayFile.of(Path.of("./some/path/File.java"), OptionalInt.of(5), OptionalInt.of(3))));
        }
    }
}