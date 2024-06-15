package demo.sw.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TodoTest {

    @Nested
    class DeserializeTests {

        @Test
        void returnDeserializedTodoWhenGivenMinimumOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Todo"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize todo entry with minimum options")
                    .isEqualTo(Document.of(Todo.empty()));
        }

        @Test
        void returnDeserializedTodoWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Todo",
                          "comments": ["Testing todo"]
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.parse(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize todo entry when given all options")
                    .isEqualTo(Document.of(Todo.of(
                            List.of("Testing todo"))));
        }
    }
}