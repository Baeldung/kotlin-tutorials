package com.baeldung.typeExposure;

public abstract class ImageSource {

    public abstract MyImage produceImage();

    static class MyImage {

    }
}