package com.baeldung.enums

interface IPrimaryColor : IColor {
    override fun type() = ColorType.PRIMARY
}