package com.baeldung.type_exposure

internal class RemoteImageSource : ImageSource() {

    override fun produceImage(): MyImage? {
        return null
    }
}