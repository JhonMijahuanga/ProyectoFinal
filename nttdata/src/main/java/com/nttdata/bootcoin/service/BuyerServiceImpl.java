package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.kafkaService.BuyerEventServiceKafka;
import com.nttdata.bootcoin.model.bean.Buyer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Slf4j
@Repository
public class BuyerServiceImpl implements BuyerService {

  public static final String HASH_KEY = "Buyer";

  @Autowired
  private RedisTemplate template;

  @Autowired
  private BuyerEventServiceKafka buyerEventServiceKafka;

  @Override
  public Buyer save(Buyer buyer) {
    this.buyerEventServiceKafka.publish(buyer);
    template.opsForHash().put(HASH_KEY,buyer.getId(),buyer);
    return buyer;
  }

  @Override
  public List<Buyer> findAll() {
    log.info("Listar todos los compradores");
    return template.opsForHash().values(HASH_KEY);
  }

  @Override
  public Buyer findBuyerId(int id) {
    log.info("Buscar por id");
    return (Buyer) template.opsForHash().get(HASH_KEY,id);
  }

  @Override
  public String deleteBuyer(int id) {
    return null;
  }
}
