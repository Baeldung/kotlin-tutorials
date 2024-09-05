package com.baeldung.constValCompilerError

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

const val MSG_PREFIX_TOP_LVL: String = "App User Event (top level):"

object AppConstants {
    const val MSG_PREFIX: String = "App User Event (in object):"
}

data class UserEvent(val name: String) {
    // ===== code doesn't compile =====
    // const val MSG_PREFIX: String = "App User Event:"
    // fun login(): String {
    //     return "$MSG_PREFIX User [$name] login successful"
    // }
    // ================================

    fun loginTopLevelPrefix(): String {
        return "$MSG_PREFIX_TOP_LVL User [$name] login successful"
    }

    fun loginObjectPrefix(): String {
        return "${AppConstants.MSG_PREFIX} User [$name] login successful"
    }

    fun loginCompanionObjPrefix(): String {
        return "$MSG_PREFIX_IN_COMPANION_OBJ User [$name] login successful"
    }

    companion object {
        const val MSG_PREFIX_IN_COMPANION_OBJ: String = "App User Event (in Companion object):"
    }
}

class ConstValCompilerErrorUnitTest {
    @Test
    fun `when using top level constant then correct`() {
        val result = UserEvent("Kai").loginTopLevelPrefix()
        assertEquals("App User Event (top level): User [Kai] login successful", result)
    }

    @Test
    fun `when using constant in object then correct`() {
        val result = UserEvent("Kai").loginObjectPrefix()
        assertEquals("App User Event (in object): User [Kai] login successful", result)
    }
    @Test
    fun `when using constant in companion object then correct`() {
        val result = UserEvent("Kai").loginCompanionObjPrefix()
        assertEquals("App User Event (in Companion object): User [Kai] login successful", result)
    }
}