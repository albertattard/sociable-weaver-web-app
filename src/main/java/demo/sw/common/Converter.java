package demo.sw.common;

import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.List;

public final class Converter {

    private static final Parser parser;
    private static final HtmlRenderer renderer;

    static {
        final MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, List.of(
                AutolinkExtension.create(),
                EmojiExtension.create(),
                StrikethroughExtension.create(),
                TaskListExtension.create(),
                TablesExtension.create()));

        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    public static String markdownToHtml(final String markdown) {
        if (markdown == null) {
            return "";
        }

        final Document parsed = parser.parse(markdown);
        return renderer.render(parsed);
    }

    private Converter() {}
}
