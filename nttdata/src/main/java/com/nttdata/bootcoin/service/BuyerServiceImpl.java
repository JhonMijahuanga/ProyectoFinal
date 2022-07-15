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
    if (buyer.getNDocument().length() == 8 || buyer.getNDocument().length()== 12){
      if (buyer.getPhone().length() == 9 && buyer.getMail()!=null){
        this.buyerEventServiceKafka.publish(buyer);
        template.opsForHash().put(HASH_KEY,buyer.getId(),buyer);//Aqui
        log.info("AGREGADO CON EXITO");
        buyer.setMessage("AGREGADO CON EXITO");

      }else{
        log.info("INGRESAR EL CORREO Y TELEFONO");
        buyer.setMessage("INGRESAR EL CORREO Y TELEFONO");
      }
    }else{
      log.info("Ingrese bien los datos (DNI, CEX o PASAPORTE");
      buyer.setMessage("Ingrese bien los datos (DNI, CEX o PASAPORTE");
    }
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

  @Override
  public Buyer updateBuyer(int id, Buyer buyer) {
    Buyer buyer1 = (Buyer) template.opsForHash().get(HASH_KEY,id);
    /*if(buyer.getId() != null){
      buyer.setId(String.valueOf(id));
    }*/
    if (buyer.getNDocument() != null){
      buyer1.setNDocument(buyer.getNDocument());
    }
    if (buyer.getMail() != null){
      buyer1.setMail(buyer.getMail());
    }
    if (buyer.getPhone() != null){
      buyer1.setPhone(buyer.getPhone());
    }
    if (buyer.getMessage() != null){
      buyer1.setMessage(buyer.getMessage());
    }
    template.opsForHash().put(HASH_KEY ,buyer1.getId(),buyer1);
    return buyer;
  }
}
