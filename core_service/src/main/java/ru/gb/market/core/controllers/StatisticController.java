package ru.gb.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.api.order.OrderDto;
import ru.gb.market.api.order.OrderItemDto;
import ru.gb.market.core.dto.statistics.ControllerStatisticDTO;
import ru.gb.market.core.services.StatisticService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @MessageMapping("/giveMeFileAboutProducts")//после того как пришел запрос с веб сокета по пути "app/giveMeFile"
    @SendTo("/topic/getFileAboutProducts")
    public String fromClientProducts() throws InterruptedException {
        Thread.sleep(10000);
       return String.valueOf(statisticService.createFileProducts());//отправляем клиенту "true/false" (создался файл или нет)
    }
//    @MessageMapping("/giveMeFileAboutOrders")//после того как пришел запрос с веб сокета по пути "app/giveMeFile"
//    @SendTo("/topic/getFileAboutOrders")
//    public String fromClientOrders() throws InterruptedException {
//        return "Hello";//отправляем клиенту "true/false" (создался файл или нет)
//    }

    @GetMapping("/")//EndPoint для получения информации о времени работы методов всех контроллеров
    public List<ControllerStatisticDTO> getAll() {
        return statisticService.getAll();
    }

    @GetMapping(value = "/getFileAboutProducts", produces = "application/octet-stream")//endPoint для скачивания файла
    public ResponseEntity<byte []> getFile() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        File file = statisticService.getExcelFileProducts();
        byte[] result = Files.readAllBytes(Path.of(file.getAbsolutePath()));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(file.getName()).build().toString());
        return ResponseEntity.ok().headers(httpHeaders).body(result);
    }
    @KafkaListener(topics = "market_topic", groupId = "group-id")
    public void listenGroupFoo(OrderDto orderDto) {
        statisticService.createFileOrders(orderDto.getItems());
        System.out.println(orderDto);
    }
}
