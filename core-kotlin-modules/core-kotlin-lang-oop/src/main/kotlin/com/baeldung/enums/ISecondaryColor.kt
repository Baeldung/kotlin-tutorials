package com.baeldung.enums

interface ISecondaryColor : IColor {
    override fun type() = ColorType.SECONDARY
}