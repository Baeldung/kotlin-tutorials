package com.baeldung.kotlin;

import kotlin.text.StringsKt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static com.baeldung.kotlin.Strings.*;


public class StringUtilUnitTest {

    @Test
    public void shouldEscapeXmlTagsInString() {
        String xml = "<a>hi</a>";

        String escapedXml = escapeForXml(xml);

        assertEquals("&lt;a&gt;hi&lt;/a&gt;", escapedXml);
    }

    @Test
    public void callingBuiltInKotlinExtensionMethod() {
        String name = "john";

        String capitalizedName = StringsKt.capitalize(name);

        assertEquals("John", capitalizedName);
    }

}
