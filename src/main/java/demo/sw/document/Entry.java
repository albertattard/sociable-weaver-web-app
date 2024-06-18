package demo.sw.document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.sw.document.breakpoint.Breakpoint;
import demo.sw.document.command.Command;
import demo.sw.document.displayfile.DisplayFile;
import demo.sw.document.heading.Heading;
import demo.sw.document.markdown.Markdown;
import demo.sw.document.todo.Todo;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Breakpoint.class, name = "Breakpoint"),
        @JsonSubTypes.Type(value = Command.class, name = "Command"),
        @JsonSubTypes.Type(value = DisplayFile.class, name = "DisplayFile"),
        @JsonSubTypes.Type(value = Heading.class, name = "Heading"),
        @JsonSubTypes.Type(value = Markdown.class, name = "Markdown"),
        @JsonSubTypes.Type(value = Todo.class, name = "Todo"),
})
public interface Entry {

    UUID getId();

    default String fragmentName() {
        final char[] chars = getClass().getSimpleName().toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
