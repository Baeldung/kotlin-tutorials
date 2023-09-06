package com.baeldung.late

class Class2 {
    lateinit var class1: Class1

    fun isInitialized(): Boolean {
        return ::class1.isInitialized
    }
}

class Class3 {
    lateinit var class2: Class2

    fun isInitialized(): Boolean {
        return ::class2.isInitialized && class2.isInitialized()
    }

    /*
     * Compilation Error Scenario
     */
    /*
    fun isInitializedDirectAccess(): Boolean {
        return ::class2.isInitialized && class2::class1.isInitialized
    }
     */
}

class Class1