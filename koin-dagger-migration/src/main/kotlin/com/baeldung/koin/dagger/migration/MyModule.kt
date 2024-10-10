package com.baeldung.koin.dagger.migration

import dagger.Provides
import dagger.Module

@Module
class MyModule {
    @Provides
    fun provideMyRepository(): MyRepository {
        return MyRepository()
    }

    @Provides
    fun provideMyService(myRepository: MyRepository): MyService {
        return MyService(myRepository)
    }
}