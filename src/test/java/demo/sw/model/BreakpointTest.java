package demo.sw.model;

import demo.sw.document.Document;
import demo.sw.document.breakpoint.Breakpoint;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BreakpointTest {

    @Nested
    class DeserializeTests {

        @Test
        void returnDeserializedBreakpointWhenGivenMinimumOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Breakpoint",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize markdown entry with minimum options")
                    .isEqualTo(Document.of(Breakpoint.empty(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"))));
        }

        @Test
        void returnDeserializedBreakpointWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Breakpoint",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
                          "comment": "Testing breakpoints"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize breakpoint entry when given all options")
                    .isEqualTo(Document.of(Breakpoint.of(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"), "Testing breakpoints")));
        }
    }
}