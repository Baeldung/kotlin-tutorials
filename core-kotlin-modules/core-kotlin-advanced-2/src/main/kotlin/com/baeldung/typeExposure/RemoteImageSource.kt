package com.baeldung.typeExposure

internal class RemoteImageSource : ImageSource() {

    override fun produceImage(): MyImage? {
        return null
    }
}