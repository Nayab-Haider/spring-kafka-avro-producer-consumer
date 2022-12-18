package com.nayab.avro.kafka.controller;

import com.nayab.avro.schema.StockHistory;
import com.nayab.avro.kafka.model.StockHistoryModel;
import com.nayab.avro.kafka.producer.SpringAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SpringAvroRestController {

    @Autowired
    private SpringAvroProducer springAvroProducer;

    @PostMapping(value = "/sendStockHistory")
    public void sendStockHistory(@RequestBody StockHistoryModel model){
        StockHistory stockHistory= StockHistory.newBuilder().build();
        stockHistory.setStockName(model.getStockName());
        stockHistory.setTradeType(model.getTradeType());
        stockHistory.setPrice(model.getPrice());
        stockHistory.setAmount(model.getAmount());
        stockHistory.setTradeId(new Random(1000).nextInt());
        stockHistory.setTradeMarket(model.getTradeMarket());
        stockHistory.setTradeQuantity(model.getTradeQuantity());
        springAvroProducer.send(stockHistory);
    }
}
