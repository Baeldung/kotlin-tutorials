package com.baeldung.theValueAnnotation

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ValueBean(
    // without default values
    @Value("\${my-app.magic-number}")
    val magicNumber: Int,
    @Value("\${my-app.magic-string}")
    val magicString: String,
    @Value("\${my-app.magic-flag}")
    val magicFlag: Boolean,

    // with default values
    @Value("\${my-app.not-defined-value:1024}")
    val magicNumberWithDefault: Int,
    @Value("\${my-app.magic-string-with-default:default Value}")
    val magicStringWithDefault: String,

    // with null as the default value
    @Value("\${my-app.not-defined-value:null}")
    val stringDefaultLiteralNull: String?,
    @Value("\${my-app.not-defined-value:#{null}}")
    val stringDefaultNull: String?
)