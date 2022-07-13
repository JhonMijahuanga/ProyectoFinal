package com.nttdata.bootcoin.model.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Transaction")
public class Transaction implements Serializable {

  @Id
  private String id;
  private String idTransaction;
  private double total;

}
