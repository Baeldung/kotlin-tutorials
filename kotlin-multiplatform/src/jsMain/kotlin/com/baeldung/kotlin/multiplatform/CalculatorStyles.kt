package com.baeldung.kotlin.multiplatform

import kotlinx.css.*
import styled.StyleSheet

object CalculatorStyles : StyleSheet("CalculatorStyles", isStatic = true) {

    val textLabel by css {
        padding(5.px)
        width = 250.px
    }

    val textInput by css {
        margin(vertical = 5.px)
        width = 250.px
        fontSize = 14.px
    }

    val textContainer by css {
        padding(5.px)
        width = 400.px
        //backgroundColor = rgb(8, 97, 22)
        //color = rgb(56, 246, 137)
    }

} 
