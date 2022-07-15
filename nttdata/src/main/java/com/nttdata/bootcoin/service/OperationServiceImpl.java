package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.kafkaService.OperationEventServiceKafka;
import com.nttdata.bootcoin.model.bean.Buyer;
import com.nttdata.bootcoin.model.bean.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class OperationServiceImpl implements OperationService {

  public static final String HASH_KEY = "Operation";

  @Autowired
  private RedisTemplate template;

  @Autowired
  private BuyerService buyerService;

  @Autowired
  private OperationEventServiceKafka serviceKafka;

  @Override
  public Operation save(Operation operation) {
    int number = (int)(Math.random()*1000000000+1);
    String idTransaction = String.valueOf(number);
    operation.setIdTransaction(idTransaction);
    log.info("EL ID GENERADO ES: " + idTransaction);
    if (operation.getIdTransaction().equals("0") ){
      log.info("No se Pudo generar codigo de transaccion");
      operation.setMessage("NO SE PUDO GENERAR EL CAMBIO");
    }
    else if (operation.getAmount()>0){
      log.info("El monto es mayor que 0 :" +operation.getAmount());
      if (operation.getPayMethod().getName().equals("YANKI") || operation.getPayMethod().getName().equals("TRANSFER")) {
        log.info("El tipo de operacion es: " + operation.getPayMethod());
        double tot = operation.getAmount() * 5;
        operation.setTotal(tot);
        operation.setMessage("SUCCESFUL TRANSACTION");
        this.serviceKafka.publish(operation);
        template.opsForHash().put(HASH_KEY, operation.getIdTransaction(), operation);
      }
    }
    return operation;
  }
  @Override
  public List<Operation> findOperationId(int id) {
    String idBuyer= String.valueOf(id);
    log.info("Entra a busqueda por ID");
    List<Operation> list = new ArrayList<Operation>();
    list=template.opsForHash().values(HASH_KEY);
    log.info("el template es: " + list);
    List<Operation> listOperation = list.stream()
        .filter(p -> p.getIdBuyer().equals(idBuyer))
        .collect(Collectors.toList());
    log.info("La lista es: " +listOperation);
    return listOperation;
  }
  @Override
  public List<Operation> findAll() {

    return template.opsForHash().values(HASH_KEY);

  }
  public Buyer findBuyerById (int id, double n){
    Buyer buyer = buyerService.findBuyerId(id);
    double total= buyer.getBalance()+n;
    buyer.setBalance(total);
    return buyer;
  }
}
