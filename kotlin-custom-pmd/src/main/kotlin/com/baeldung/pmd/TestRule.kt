package com.baeldung.pmd

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule
import java.util.*

class TestRule : AbstractJavaRule() {

    override fun visit(node: ASTClassOrInterfaceDeclaration, data: Any): Any {
        val className = node.simpleName
        println("class name: $className")
        Objects.requireNonNull(className)
        if (className.endsWith("SpringContextTest")) {
            return data
        }
        if (className.endsWith("Tests") || className.endsWith("Test") && allowedEndings.stream().noneMatch { suffix: String? -> className.endsWith(suffix!!) }) {
            addViolation(data, node)
        }
        return data
    }

    companion object {
        private val allowedEndings: List<String> = mutableListOf(
                "IntegrationTest",
                "IntTest",
                "ManualTest",
                "JdbcTest",
                "LiveTest",
                "UnitTest",
                "jmhTest")
    }
}