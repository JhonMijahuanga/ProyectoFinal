package com.nttdata.bootcoin.model.bean;

import com.nttdata.bootcoin.model.bean.enums.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Operation")
public class Operation implements Serializable {

  @Id
  private String idTransaction;
  private String idBuyer;
  private double amount; //Monto
  private PayMethod payMethod; //YANKI("yanki"), TRANSFER("transfer");
  private double total;
  private String message;
}
