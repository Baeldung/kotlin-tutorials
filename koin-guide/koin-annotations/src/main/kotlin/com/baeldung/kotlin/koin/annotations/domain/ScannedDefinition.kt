package com.baeldung.kotlin.koin.annotations.domain

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

@Single
class ScannedSingletonComponent

@Factory
class ScannedFactoryComponent

