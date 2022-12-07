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
import ru.gb.market.api.order.OrderItemDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@Getter
@NoArgsConstructor
@Slf4j
public class ExcelUtilOrders {
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet sheet = workbook.createSheet("Orders");
    private final String FILE_NAME = "orders.xlsx";
    private final File file = new File(FILE_NAME);
    private int rowNumber;
    private Cell cell;
    private Row row;

    public boolean creatFile(List<OrderItemDto> orderItemDtos) {
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                this.workbook = XSSFWorkbookFactory.createWorkbook(OPCPackage.open(fis));
            } catch (IOException | InvalidFormatException e) {
                log.debug(e.toString());
                return false;
            }
        } else {
            config();
        }
        writeProductInSheet(orderItemDtos);
        FileOutputStream outFile;
        try {
            outFile = new FileOutputStream(file, false);
            workbook.write(outFile);
            outFile.close();
        } catch (IOException e) {
            log.info(e.toString());
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
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("№");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ProductTitle");
        cell.setCellStyle(style);
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("Count");
        cell.setCellStyle(style);
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue("PricePerProduct");
        cell.setCellStyle(style);
        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue("FullPrice");
        cell.setCellStyle(style);
    }

    private void writeProductInSheet(List<OrderItemDto> orderItemDtos) {
        rowNumber = workbook.getSheet("Orders").getLastRowNum();
        sheet = workbook.getSheet("Orders");
        sheet.shiftRows(rowNumber, rowNumber + orderItemDtos.size(), orderItemDtos.size(), true, true);
        rowNumber--;
        for (OrderItemDto itemDto : orderItemDtos) {
            rowNumber++;
            row = sheet.createRow(rowNumber);
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(itemDto.getProductId());
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(itemDto.getProductTitle());
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(itemDto.getCount());
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(itemDto.getPricePerProduct().doubleValue());
            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(itemDto.getPrice().doubleValue());
        }
    }


}