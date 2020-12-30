package com.baeldung.kotlin.multiplatform

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.*

external interface CalculatorProps : RProps {
    var value: String
}

data class CalculatorState(val value: String) : RState

@JsExport
class Calculator(props: CalculatorProps) : RComponent<CalculatorProps, CalculatorState>(props) {

    init {
        state = CalculatorState(props.value)
    }

    override fun RBuilder.render() {
        styledLabel {
            css {
                +CalculatorStyles.textLabel
            }
            + "Enter a Number: "
        }

        styledInput {
            css {
                +CalculatorStyles.textInput
            }

            attrs {
                type = InputType.number
                value = state.value
                onChangeFunction = { event ->
                    setState(
                            CalculatorState(value = (event.target as HTMLInputElement).value)
                    )
                }
            }
        }

        styledDiv {
            css {
                +CalculatorStyles.textContainer
            }
            +"Square of the Input: ${
                state.value.toDouble()*state.value.toDouble()}"
        }
    }
}
