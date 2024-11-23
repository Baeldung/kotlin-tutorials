package com.baeldung.kotlin.kandy

import org.jetbrains.kotlinx.dataframe.api.reverse
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import org.jetbrains.kotlinx.kandy.letsplot.scales.categoricalColorHue
import org.jetbrains.kotlinx.kandy.util.color.Color

class BarChartPlotter {
    val tiobeIndex = mapOf(
        "Programming Language" to listOf("Python", "C++", "Java", "C", "C#"),
        "Ratings" to listOf(21.90, 11.60, 10.51, 8.38, 5.62)
    )

    fun plotSimpleBarChart() {
        tiobeIndex.plot {
            bars {
                x("Programming Language")
                y("Ratings")
            }
        }.save("TiobeIndexSimple.png", path = "src/test/resources/charts")
    }

    fun plotCustomBarChart() {
        tiobeIndex
            .toDataFrame()
            .reverse()
            .plot {
                barsH {
                    x("Ratings")
                    y("Programming Language")

                    alpha = 0.75
                    fillColor("Programming Language") {
                        scale = categoricalColorHue(chroma = 80, luminance = 70)
                        borderLine.color = Color.BLACK
                        borderLine.width = 0.5
                        legend.name = "Programming\n Language"
                    }
                }
                layout {
                    title = "Tiobe Index October 2024"
                    subtitle = "Top 5 Programming Languages"
                    caption = "Source: www.tiobe.com"
                    size = 800 to 600

                }
            }.save("TiobeIndexCustom.png", path = "src/test/resources/charts")
    }

}