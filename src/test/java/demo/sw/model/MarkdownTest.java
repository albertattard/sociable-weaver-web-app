package demo.sw.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MarkdownTest {

    @Nested
    class DeserializeTests {

        @Test
        void returnDeserializedMarkdownWhenGivenMinimumOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Markdown",
                          "contents": [
                            "We make mistakes, and we make more mistakes, and some more, and that's how we learn."
                          ]
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize markdown entry with minimum options")
                    .isEqualTo(Document.of(Markdown.of(List.of("We make mistakes, and we make more mistakes, and some more, and that's how we learn."))));
        }

        @Test
        void returnDeserializedMarkdownWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Markdown",
                          "contents": [
                            "We make mistakes, and we make more mistakes, and some more, and that's how we learn."
                          ],
                          "tags": [
                            "test"
                          ]
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize markdown entry when given all options")
                    .isEqualTo(Document.of(Markdown.of(
                            List.of("We make mistakes, and we make more mistakes, and some more, and that's how we learn."),
                            Set.of("test"))));
        }
    }
}