package ru.gb.market.core.utils.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import ru.gb.market.core.entities.Product;

import java.io.*;
import java.util.List;

@Component
@Getter
@NoArgsConstructor
@Slf4j
public class ExcelUtilProducts {
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private final XSSFSheet sheet = workbook.createSheet("Products");
    private final String FILE_NAME = "products.xlsx";
    private final File file = new File(FILE_NAME);
    private int rowNumber;
    private Cell cell;
    private Row row;

    public boolean creatFile(List<Product> products) {
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = XSSFWorkbookFactory.createWorkbook(OPCPackage.open(fis));
            } catch (IOException | InvalidFormatException e) {
                log.debug(e.toString());
                return false;
            }
        } else {
            config();
        }
        writeProductInSheet(products);
        FileOutputStream outFile;
        try {
            outFile = new FileOutputStream(file, false);
            workbook.write(outFile);
            outFile.close();
        } catch (IOException e) {
            log.debug(e.toString());
            return false;
        }
        log.info("Created file: " + file.getAbsolutePath());
        return true;
    }

    private void config() {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        row = sheet.createRow(rowNumber);
        // Product ID
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("№");
        cell.setCellStyle(style);
        // Product title
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Title");
        cell.setCellStyle(style);
        // Price
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("Price");
        cell.setCellStyle(style);
        // Count
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue("Count");
        cell.setCellStyle(style);
    }

    private void writeProductInSheet(List<Product> products) {
        for (Product product : products) {
            rowNumber++;
            row = sheet.createRow(rowNumber);
            // Product ID
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(product.getId());
            // Product Title
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(product.getTitle());
            // Price
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(product.getPrice().doubleValue());
            // Count
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(product.getCount());
        }
    }


}