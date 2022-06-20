package com.baeldung.csv.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TaxableGood(
    @field:JsonProperty("Index") val index: Int,
    @field:JsonProperty("Item") val item: String?,
    @field:JsonProperty("Cost") val cost: BigDecimal?,
    @field:JsonProperty("Tax") val tax: BigDecimal?,
    @field:JsonProperty("Total") val total: BigDecimal?
) {
    constructor() : this(0, "", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)
}