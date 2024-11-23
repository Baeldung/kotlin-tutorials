package com.baeldung.kotlin.kandy

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.*
import org.jetbrains.kotlinx.kandy.dsl.continuous

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.facetWrap

class MultiplotChart {
    var bigMacData = DataFrame.readCSV("src/main/resources/BigMac Index.csv")

    val sortedBigMacData = bigMacData
        .filter { it["name"] in listOf("United States", "Canada", "China", "Euro area") }
        .sortBy("name")

    fun plotMultiplotChart() {
        sortedBigMacData.plot {
            points {
                x("date") {
                    axis.name = "Date"
                }
                y("dollar_price") {
                    scale = continuous(0.0..8.0)
                    axis.name = "USD Price"
                }

                color("name")
                size = 2.0
            }

            facetWrap(nCol = 2) {
                facet(bigMacData["name"])
            }
            layout {
                title = "Big Mac Index"
                subtitle = "USD Price of a Big Mac in Different Countries"
                size = 800 to 600
            }
        }.save("BigMacData.png", path = "src/test/resources/charts")
    }
}