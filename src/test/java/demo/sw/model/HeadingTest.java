package demo.sw.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
                          "level": "H1",
                          "title": "Prologue"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize heading entry with minimum options")
                    .isEqualTo(Document.of(Heading.h1("Prologue")));
        }
    }

    @Nested
    class AsHtmlTest {
        @Test
        void returnFormattedHeading() {
            final String html = Heading.h1("Prologue").titleAsHtml();

            assertThat(html)
                    .describedAs("Format heading entry at level 1")
                    .isEqualTo("Prologue");
        }

        @Test
        void returnFormattedHeadingWithMarkdown() {
            final String html = Heading.h3("Version control (_Optional_)").titleAsHtml();

            assertThat(html)
                    .describedAs("Format heading entry at level 1")
                    .isEqualTo("Version control (<em>Optional</em>)");
        }
    }
}
