package com.baeldung

import java.io.ByteArrayInputStream
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class XmlParsingService private constructor() {

    companion object {
        private var factory: DocumentBuilderFactory = DocumentBuilderFactory.newDefaultInstance()

        fun extractIdFromXmlEntity(xml: String): UUID {
            val document = factory.newDocumentBuilder().parse(ByteArrayInputStream(xml.toByteArray()))
            val node = document.getElementsByTagName("entityId").item(0)
            return UUID.fromString(node.textContent.toString());
        }
    }
}