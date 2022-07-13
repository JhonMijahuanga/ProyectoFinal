package com.nttdata.bootcoin.controller;

import com.nttdata.bootcoin.model.bean.Operation;
import com.nttdata.bootcoin.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/operation")
@EnableCaching
public class OperationController {

  @Autowired
  private OperationService operationService;

  @PostMapping
  public Operation saveOperation(@RequestBody Operation operation){

    return operationService.save(operation);

  }

  @GetMapping
  public List<Operation> findOperationAll(){

    return operationService.findAll();
  }

}
