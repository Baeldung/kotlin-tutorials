package com.baeldung.kandy

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.candlestick

class CandlestickChartPlotter {
    val bitcoinData = DataFrame.readCSV("src/main/resources/BTC 1y Data.csv", delimiter = ';')
    val windowSize = 5

    fun plotSimpleCandlestickChart() {
        bitcoinData.plot {
            candlestick("timestamp", "open", "high", "low", "close")
        }.save("BitcoinCandlestickSimple.png", path = "src/test/resources/charts")
    }

    fun plotCustomCandlestickChart() {
        val movingAverages = bitcoinData["close"].toList().map { it as Double }
        val movingAverage = DataUtils.calculateMovingAverage(windowSize, movingAverages)

        bitcoinData.plot {
            candlestick("timestamp", "open", "high", "low", "close") {
                x.axis.name = "Date"
                alpha(Stat.isIncreased) {
                    scale = categorical(true to 1.0, false to 0.05)
                    legend {
                        name = ""
                        breaksLabeled(true to "Increase", false to "Decrease")
                    }
                }
                fillColor = Color.GREEN
                borderLine.color = Color.GREY
            }

            line {
                y(movingAverage)
                x("timestamp")
            }

            layout {
                title = "BTC/USD"
                size = 800 to 500
            }
        }.save("BitcoinCandlestickCustom.png", path = "src/test/resources/charts")
    }


}
