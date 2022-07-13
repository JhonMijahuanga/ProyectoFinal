package com.nttdata.bootcoin.model.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Buyer")
public class Buyer implements Serializable {

  @Id
  private String id;
  private String nDocument;
  private String phone;
  private String mail;
  private double balance; //saldo

}
