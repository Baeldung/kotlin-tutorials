package com.baeldung.dagger

import dagger.Module
import dagger.Provides
import io.mockk.every
import io.mockk.mockk
import javax.inject.Named

@Module
class AppModule {
    @Provides
    @Named("debug")
    fun provideDebugApiService(): ApiService = DebugApiService()

    @Provides
    @Named("release")
    fun provideReleaseApiService(): ApiService = ReleaseApiService()
}

@Module
class AppModuleConditional {

    @Provides
    @Named("apiService")
    fun provideApiService(): ApiService {
        val environment = System.getenv("APP_ENV") ?: "production"
        return if (environment == "development") {
            DebugApiService()
        } else {
            ReleaseApiService()
        }
    }
}

@Module
class TestAppModule {
    @Provides
    @Named("debug")
    fun provideMockDebugApiService(): ApiService = mockk {
        every { getData() } returns "Mocked Debug Data"
    }

    @Provides
    @Named("release")
    fun provideMockReleaseApiService(): ApiService = mockk {
        every { getData() } returns "Mocked Release Data"
    }
}