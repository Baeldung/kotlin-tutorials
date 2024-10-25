package com.baeldung.dagger

interface ApiService {
    fun getData(): String
}

class DebugApiService : ApiService {
    override fun getData(): String {
        return "Debug Data"
    }
}

class ReleaseApiService : ApiService {
    override fun getData(): String {
        return "Release Data"
    }
}