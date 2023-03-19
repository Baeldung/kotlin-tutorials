package com.baeldung.excel

import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.createTempFile
import kotlin.io.path.outputStream


class ReadExcelFileTest {

    @Test
    fun `when file is read then content is correct`() {
        val inputStream = this::class.java.getResourceAsStream("test_input.xlsx")
        val workbook = WorkbookFactory.create(inputStream)

        val workSheet = workbook.getSheetAt(0)
        assertThat(workSheet.getRow(0).getCell(0).stringCellValue).isEqualTo("TEST VALUE")
    }

    @Test
    fun `when file is created then content is correct`() {
        val workbook = XSSFWorkbook()
        val workSheet = workbook.createSheet()
        val cellStyle = workbook.createCellStyle()
        cellStyle.fillForegroundColor = IndexedColors.RED.getIndex()
        cellStyle.fillPattern = FillPatternType.SOLID_FOREGROUND
        val firstCell = workSheet
            .createRow(0)
            .createCell(0)
        firstCell.setCellValue("SAVED VALUE")
        firstCell.cellStyle = cellStyle


        val tempFile = createTempFile("test_output_", ".xlsx")
        workbook.write(tempFile.outputStream())
        workbook.close()

        val inputWorkbook = WorkbookFactory.create(tempFile.toFile().inputStream())
        val firstSheet = inputWorkbook.getSheetAt(0)
        assertThat(firstSheet.getRow(0).getCell(0).stringCellValue).isEqualTo("SAVED VALUE")
    }
}