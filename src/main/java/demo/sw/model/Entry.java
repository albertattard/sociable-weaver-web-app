package demo.sw.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Breakpoint.class, name = "Breakpoint"),
        @JsonSubTypes.Type(value = Command.class, name = "Command"),
        @JsonSubTypes.Type(value = DisplayFile.class, name = "DisplayFile"),
        @JsonSubTypes.Type(value = Heading.class, name = "Heading"),
        @JsonSubTypes.Type(value = Markdown.class, name = "Markdown"),
        @JsonSubTypes.Type(value = Todo.class, name = "Todo"),
})
public sealed interface Entry permits Breakpoint, Command, DisplayFile, Heading, Markdown, Todo {

    default String fragmentName() {
        final char[] chars = getClass().getSimpleName().toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
