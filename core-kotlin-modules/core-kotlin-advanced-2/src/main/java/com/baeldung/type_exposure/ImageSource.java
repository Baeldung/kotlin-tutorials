package com.baeldung.type_exposure;

public abstract class ImageSource {

    public abstract MyImage produceImage();

    static class MyImage {

    }
}