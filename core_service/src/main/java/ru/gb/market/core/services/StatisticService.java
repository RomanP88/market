package ru.gb.market.core.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.order.OrderItemDto;
import ru.gb.market.core.utils.excel.ExcelUtilOrders;
import ru.gb.market.core.utils.excel.ExcelUtilProducts;
import ru.gb.market.core.dto.statistics.ControllerStatisticDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class StatisticService {
    private final List<ControllerStatisticDTO> list = new ArrayList<>();
    private final ExcelUtilProducts excelUtilProducts;
    private final ExcelUtilOrders excelUtilOrders;
    private final ProductService productService;
    public List<ControllerStatisticDTO> getAll(){
        return list;
    }
    public File getExcelFileProducts() throws IOException {
        return excelUtilProducts.getFile();
    }
    public File getExcelFileOrders() throws IOException {
        return excelUtilOrders.getFile();
    }

    public boolean createFileProducts(){
       return excelUtilProducts.creatFile(productService.findAll());
    }
    public boolean createFileOrders(List<OrderItemDto> itemDtos){
        return excelUtilOrders.creatFile(itemDtos);
    }
}
