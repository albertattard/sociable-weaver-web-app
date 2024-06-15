package demo.sw.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
                          "type": "Breakpoint"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize markdown entry with minimum options")
                    .isEqualTo(Document.of(Breakpoint.empty()));
        }

        @Test
        void returnDeserializedBreakpointWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                       "entries": [
                         {
                           "type": "Breakpoint",
                           "comment": "Testing breakpoints"
                         }
                       ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize breakpoint entry when given all options")
                    .isEqualTo(Document.of(Breakpoint.of("Testing breakpoints")));
        }
    }
}