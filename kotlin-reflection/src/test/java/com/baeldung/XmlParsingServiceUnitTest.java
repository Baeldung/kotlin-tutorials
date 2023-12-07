package com.baeldung;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class XmlParsingServiceUnitTest {

    @Test
    void givenCompanionObjectInClass_whenAccessingStaticFieldInEnclosingObject_thenFoundInEnclosingClass() throws NoSuchFieldException {
        Class<XmlParsingService> xmlParsingServiceClass = XmlParsingService.class;
        Field factory = xmlParsingServiceClass.getDeclaredField("factory");
        Assertions.assertThat(factory.getType()).isSameAs(DocumentBuilderFactory.class);
    }

    @Test
    void givenCompanionObjectInClass_whenIteratingOverInnerClassesOfEnclosingClass_thenNestedCompanionClassFound() throws ClassNotFoundException {
        Class<XmlParsingService> xmlParsingServiceClass = XmlParsingService.class;
        Class<?>[] classes = xmlParsingServiceClass.getClasses();
        Assertions.assertThat(Arrays.asList(classes))
            .hasSize(1)
            .asList()
            .first()
            .isEqualTo(Class.forName("com.baeldung.XmlParsingService$Companion"));
    }

    @Test
    void givenCompanionObject_whenInvokingMethodViaReflection_thenEntityIdExtractedSuccessfully() throws Exception {
        Class<XmlParsingService> xmlParsingServiceClass = XmlParsingService.class;
        Field companion = xmlParsingServiceClass.getDeclaredField("Companion");
        companion.setAccessible(true);
        Object companionInstance = companion.get(null);
        Class<?> companionClass = Class.forName("com.baeldung.XmlParsingService$Companion");
        Method extractIdFromXmlEntity = companionClass.getDeclaredMethod("extractIdFromXmlEntity", String.class);
        extractIdFromXmlEntity.setAccessible(true);
        Object result = extractIdFromXmlEntity.invoke(
          companionInstance, "<entities><entityId>8d15c2f7-635f-4730-ad60-92e2b117c4bc</entityId></entities>");
        Assertions.assertThat(result).isEqualTo(UUID.fromString("8d15c2f7-635f-4730-ad60-92e2b117c4bc"));
    }
}