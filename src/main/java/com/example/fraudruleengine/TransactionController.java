package com.example.fraudruleengine;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController  {

    @PostMapping
    public Transaction receiveTransaction(@RequestBody Transaction transaction){
        return transaction;
    }
}
