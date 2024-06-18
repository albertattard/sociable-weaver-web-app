package demo.sw.model;

import demo.sw.document.Document;
import demo.sw.document.markdown.Markdown;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
                          "contents": [
                            "We make mistakes, and we make more mistakes, and some more, and that's how we learn."
                          ]
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize markdown entry with minimum options")
                    .isEqualTo(Document.of(Markdown.of(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"),List.of("We make mistakes, and we make more mistakes, and some more, and that's how we learn."))));
        }

        @Test
        void returnDeserializedMarkdownWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Markdown",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
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
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize markdown entry when given all options")
                    .isEqualTo(Document.of(Markdown.of(
                            UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"),
                            List.of("We make mistakes, and we make more mistakes, and some more, and that's how we learn."),
                            Set.of("test"))));
        }
    }
}