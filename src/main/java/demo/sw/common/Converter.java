package demo.sw.common;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.List;

import static java.util.Objects.requireNonNull;

public final class Converter {

    private static final Parser parser;
    private static final HtmlRenderer renderer;

    static {
        final MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, List.of(TablesExtension.create()));

        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    public static String markdownToHtml(final String markdown) {
        requireNonNull(markdown);

        final Document parsed = parser.parse(markdown);
        return renderer.render(parsed);
    }

    private Converter() {}
}
