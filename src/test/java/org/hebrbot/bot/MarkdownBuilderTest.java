package org.hebrbot.bot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkdownBuilderTest {

    MarkdownBuilder m;

    @Before
    public void init() {
         m = new MarkdownBuilder();
    }

    @Test
    public void bold1() {
        assertEquals("*text*",m.bold("text").toString());
    }

    @Test
    public void italic1() {
        assertEquals("_text\ntext_",m.italic("text\ntext").toString());
    }

    @Test
    public void underline1() {
        assertEquals("__text\ntext__",m.underline("text\ntext").toString());
    }

    @Test
    public void strikethrough1() {
        assertEquals("~text\ntext~",m.strikethrough("text\ntext").toString());
    }

    @Test
    public void formatted() {
    }

    @Test
    public void url() {
    }

    @Test
    public void preFormatCodeText() {
    }

    @Test
    public void preFormatText() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void bold() {
    }

    @Test
    public void italic() {
    }

    @Test
    public void underline() {
    }

    @Test
    public void strikethrough() {
    }

    @Test
    public void toHtml1() {
        assertEquals("<p><em>text</em></p>", m.bold("text").toHtml());
    }

    @Test
    public void escape1() {
        assertEquals("*bold \\*text*",m.bold("bold *text").toString());
    }
}