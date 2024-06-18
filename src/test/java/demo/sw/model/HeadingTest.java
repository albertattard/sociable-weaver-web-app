package demo.sw.model;

import demo.sw.document.Document;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static demo.sw.document.heading.Heading.Level.H1;
import static demo.sw.document.heading.Heading.Level.H3;
import static org.assertj.core.api.Assertions.assertThat;

class HeadingTest {

    @Nested
    class DeserializeTests {

        @Test
        void returnDeserializedHeading() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Heading",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
                          "level": "H1",
                          "title": "Prologue"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize heading entry with minimum options")
                    .isEqualTo(Document.of(H1.heading(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"), "Prologue")));
        }
    }

    @Nested
    class AsHtmlTest {
        @Test
        void returnFormattedHeading() {
            final String html = H1.heading(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"), "Prologue").getTitleAsHtml();

            assertThat(html)
                    .describedAs("Format heading entry at level 1")
                    .isEqualTo("Prologue");
        }

        @Test
        void returnFormattedHeadingWithMarkdown() {
            final String html = H3.heading(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"), "Version control (_Optional_)").getTitleAsHtml();

            assertThat(html)
                    .describedAs("Format heading entry at level 1")
                    .isEqualTo("Version control (<em>Optional</em>)");
        }
    }
}
