package demo.sw.model;

import demo.sw.document.Document;
import demo.sw.document.todo.Todo;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

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
                          "type": "Todo",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e"
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize todo entry with minimum options")
                    .isEqualTo(Document.of(Todo.empty(UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"))));
        }

        @Test
        void returnDeserializedTodoWhenGivenAllOptions() {
            /* Given */
            final String json = """
                    {
                      "entries": [
                        {
                          "type": "Todo",
                          "id": "9bb63455-3916-4c01-a3a2-489ef6a7aa8e",
                          "comments": ["Testing todo"]
                        }
                      ]
                    }
                    """;

            /* When */
            final Document deserialized = Document.readDocument(json);

            /* Then */
            assertThat(deserialized)
                    .describedAs("Deserialize todo entry when given all options")
                    .isEqualTo(Document.of(Todo.of(
                            UUID.fromString("9bb63455-3916-4c01-a3a2-489ef6a7aa8e"),
                            List.of("Testing todo"))));
        }
    }
}