package com.baeldung.ktlintcustomrule

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class TestNamingRule :
    Rule(
        ruleId = RuleId("$CUSTOM_RULE_SET_ID:unit-test-convention"),
        about = About(maintainer = "Gaetano Piazzolla"),
    ) {

    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        node
            .takeIf { node.elementType == ElementType.CLASS  }
            ?.findChildByType(ElementType.IDENTIFIER)
            ?.takeIf { it.isTest() && !it.isValidName(allowedEndings) }
            ?.let {
                emit(it.startOffset, "Unit test class names need to end in 'UnitTest', integration tests with 'IntegrationTest', etc", false)
            }
    }

    private fun ASTNode.isTest() = text.endsWith("Test")
    private fun ASTNode.isValidName(endings: List<String>): Boolean {
        for (ending in endings) {
            if (text.endsWith(ending, false)) return true
        }
        return false
    }

    private companion object {
        val allowedEndings: List<String> = mutableListOf(
            "IntegrationTest",
            "IntTest",
            "ManualTest",
            "JdbcTest",
            "LiveTest",
            "UnitTest",
            "jmhTest"
        )
    }

}