package com.baeldung.kotlin.kandy.com.baeldung.kandy

import com.baeldung.kotlin.kandy.BarChartPlotter
import com.baeldung.kotlin.kandy.CandlestickChartPlotter
import com.baeldung.kotlin.kandy.MultiplotChart
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class ChartPlotterUnitTests {
    @Test
    fun `Plot a simple bar chart`() {
        val simpleBarChart = BarChartPlotter()

        simpleBarChart.plotSimpleBarChart()

        assertThat(File("src/test/resources/charts/TiobeIndexSimple.png").exists()).isTrue
    }

    @Test
    fun `Plot a custom bar chart`() {
        val customBarChart = BarChartPlotter()

        customBarChart.plotCustomBarChart()

        assertThat(File("src/test/resources/charts/TiobeIndexCustom.png").exists()).isTrue
    }

    @Test
    fun `Plot a simple candlestick chart`() {
        val simpleCandlestickChart = CandlestickChartPlotter()

        simpleCandlestickChart.plotSimpleCandlestickChart()

        assertThat(File("src/test/resources/charts/BitcoinCandlestickSimple.png").exists()).isTrue
    }

    @Test
    fun `Plot a custom candlestick chart`() {
        val customCandlestickChart = CandlestickChartPlotter()

        customCandlestickChart.plotCustomCandlestickChart()

        assertThat(File("src/test/resources/charts/BitcoinCandlestickCustom.png").exists()).isTrue
    }

    @Test
    fun `Plot a multiplot chart`() {
        val multiplotChart = MultiplotChart()

        multiplotChart.plotMultiplotChart()

        assertThat(File("src/test/resources/charts/BigMacData.png").exists()).isTrue
    }
}