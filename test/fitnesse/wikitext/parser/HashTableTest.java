package fitnesse.wikitext.parser;

import fitnesse.html.HtmlElement;
import org.junit.Test;

public class HashTableTest {
    @Test public void scansHashTables() {
        ParserTestHelper.assertScansTokenType("!{a:b,c:d}", "HashTable", true);
        ParserTestHelper.assertScansTokenType("!{a:b,c:d}", "Colon", true);
        ParserTestHelper.assertScansTokenType("!{a:b,c:d}", "Comma", true);
    }

    @Test public void translatesHashTables() {
        ParserTestHelper.assertTranslatesTo("!{a:b,c:d}", hashTable());
        ParserTestHelper.assertTranslatesTo("!{a:b, c:d}", hashTable());
    }

    @Test public void invalidHashInTableIsIgnored() {
        ParserTestHelper.assertTranslatesTo("|!{a:}|\n", ParserTestHelper.tableWithCell("!{a:}"));
    }

    private String hashTable() {
        return ("<table class=\"hash_table\">" + HtmlElement.endl +
                "<tr class=\"hash_row\">" + HtmlElement.endl +
                "<td class=\"hash_key\">a</td>" + HtmlElement.endl +
                "<td class=\"hash_value\">b</td>" + HtmlElement.endl +
                "</tr>" + HtmlElement.endl +
                "<tr class=\"hash_row\">" + HtmlElement.endl +
                "<td class=\"hash_key\">c</td>" + HtmlElement.endl +
                "<td class=\"hash_value\">d</td>" + HtmlElement.endl +
                "</tr>" + HtmlElement.endl +
                "</table>" + HtmlElement.endl)
                .replaceAll("(\n\r?|\r\n?)", "")
                .replaceAll("\\s+", " ");
    }
}
