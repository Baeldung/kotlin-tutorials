package com.baeldung.kotlin.multiplatform

import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(Calculator::class) {
                attrs {
                    value = "0"
                }
            }
        }
    }
}
