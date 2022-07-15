package com.nttdata.bootcoin.controller;

import com.nttdata.bootcoin.model.bean.Buyer;
import com.nttdata.bootcoin.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@SpringBootApplication
@RestController
@RequestMapping("/api/v1/buyer")
@EnableCaching
public class BuyerController {

  @Autowired
  private BuyerService buyerService;

  @PostMapping
  public Buyer saveBuyer(@RequestBody Buyer buyer){
    return buyerService.save(buyer);
  }

  @GetMapping
  public List<Buyer> getAllBuyer(){
    log.info("Entro al metodo GetMapping");
    return buyerService.findAll();
  }

  @GetMapping("/{id}")
  public Buyer findBuyerById(@PathVariable int id){
    return buyerService.findBuyerId(id);
  }

}
